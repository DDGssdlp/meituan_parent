package com.ddg.meituan.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.ddg.meituan.common.exception.MeituanCodeEnum;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.R;
import com.ddg.meituan.member.constant.MemberConstant;
import com.ddg.meituan.member.vo.MemberRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.Query;

import com.ddg.meituan.member.dao.MemberDao;
import com.ddg.meituan.member.entity.MemberEntity;
import com.ddg.meituan.member.service.MemberService;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Resource
    private MemberDao memberDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R register(MemberRegisterVo memberRegisterVo) throws MeituanSysException {
        MemberEntity memberEntity = new MemberEntity();
        // 进行注册：逻辑
        String username = memberRegisterVo.getUserName();
        String phoneNum = memberRegisterVo.getPhoneNum();
        String password = memberRegisterVo.getPassword();
        // 密码进行加密
        String digestPassword = passwordEncoder.encode(password);

        // 检查用户名称 手机号是否是唯一：
        boolean isUnique = checkPhoneNum(phoneNum);
        if (isUnique) {
            memberEntity.setUsername(username);
            memberEntity.setPassword(digestPassword);
            memberEntity.setMobile(phoneNum);
            int insert = memberDao.insert(memberEntity);
            return R.ok().put("register", insert);
        } else {
            throw new MeituanSysException("手机号或者是用户名称已占用！");
        }
    }

    @Override
    public boolean checkPhoneNum(String phoneNum) {
        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(MemberConstant.MOBILE_COLUMN, phoneNum);
        Integer count = memberDao.selectCount(wrapper);
        return count == 0;
    }

    @Override
    public boolean checkUsername(String username) {
        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(MemberConstant.USERNAME_COLUMN, username);
        Integer count = memberDao.selectCount(wrapper);
        return count == 0;
    }


    @Override
    public R login(MemberRegisterVo memberRegisterVo, HttpSession session) throws MeituanSysException {
        String phone = memberRegisterVo.getUserName();
        String phoneCode = memberRegisterVo.getPhoneCode();
        String password = memberRegisterVo.getPassword();

        if (!StringUtils.isEmpty(phoneCode)) {
            // 要是传入的有验证码 使用手机号进行登录的
            String phoneCodeFromRedis = redisTemplate.opsForValue()
                    .get(MemberConstant.REDIS_PHONE_CODE_PREFIX + phone);
            if (!StringUtils.isEmpty(phoneCodeFromRedis)) {
                phoneCodeFromRedis = phoneCodeFromRedis.split("_")[0];
            } else {
                if (MemberConstant.PHONE_CODE_MOCK.equals(phoneCode)) {
                    phoneCodeFromRedis = phoneCode;
                }
            }
            if (phoneCode.equals(phoneCodeFromRedis)) {
                // 验证成功 能进行登录：可以将登录的用户信息存储到 redis 中 这里的username 是手机号
                // 使用手机号进行查询
                QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
                wrapper.eq(MemberConstant.MOBILE_COLUMN, phone);
                MemberEntity memberEntity = memberDao.selectOne(wrapper);
                if (memberEntity == null) {
                    // 没有直接进行注册：
                    String codeString = UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0,
                            MemberConstant.MEMBER_CODE_LENGTH);
                    memberRegisterVo.setUserName(MemberConstant.MEITUAN_USERNAME_PREFIX + codeString);
                    memberRegisterVo.setPhoneNum(phone);
                    memberRegisterVo.setPassword(MemberConstant.DEFAULT_PASSWORD);
                    register(memberRegisterVo);
                } else {
                    memberRegisterVo.setUserName(memberEntity.getUsername());
                    memberRegisterVo.setPhoneNum(memberEntity.getMobile());
                }

                redisTemplate.opsForHash().put(MemberConstant.REDIS_CACHE_LOGIN_USER_KEY, phone,
                        JSON.toJSONString(memberRegisterVo));

                // 将验证码删除
                redisTemplate.delete(MemberConstant.REDIS_PHONE_CODE_PREFIX + phone);
                session.setAttribute(MemberConstant.LOGIN_USER, memberRegisterVo);
                return R.ok().put("login", memberRegisterVo);
            } else {
                R.error().put("login", "验证码失效！");
            }

        } else {
            // 使用账号密码进行登录的
            QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
            // 密码进行加密
            //password = passwordEncoder.encode(password);
            //  手机号：查询
            wrapper.eq(MemberConstant.MOBILE_COLUMN, phone);
            MemberEntity memberEntity = memberDao.selectOne(wrapper);
            if (memberEntity != null) {
                // 同样的能就能进行登录：将用户信息进行缓存
                String passwordFromSQL = memberEntity.getPassword();
                if (!StringUtils.isEmpty(passwordFromSQL)) {
                    // 进行密码的匹配：
                    if (passwordEncoder.matches(password, passwordFromSQL)) {
                        memberRegisterVo.setUserName(memberEntity.getUsername());
                        memberRegisterVo.setPhoneNum(memberEntity.getMobile());
                        memberRegisterVo.setPassword(memberEntity.getPassword());
                        redisTemplate.opsForHash().put(MemberConstant.REDIS_CACHE_LOGIN_USER_KEY, phone,
                                JSON.toJSONString(memberRegisterVo));
                        session.setAttribute(MemberConstant.LOGIN_USER, memberRegisterVo);
                        return R.ok().put("login", memberRegisterVo);
                    }
                }

            } else {
                return R.error().put("login", "手机号没有被进行注册！");
            }
        }
        return R.error().put("login", "账号或者是密码不正确！");

    }

}