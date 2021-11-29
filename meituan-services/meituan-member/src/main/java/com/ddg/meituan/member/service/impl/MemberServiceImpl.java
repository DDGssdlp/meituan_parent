package com.ddg.meituan.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.constant.AuthConstant;
import com.ddg.meituan.common.domain.UserDto;
import com.ddg.meituan.common.exception.MeituanLoginException;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.*;
import com.ddg.meituan.member.constant.MemberConstant;
import com.ddg.meituan.member.entity.MemberLoginLogEntity;
import com.ddg.meituan.member.service.MemberLoginLogService;
import com.ddg.meituan.member.vo.MemberRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ddg.meituan.member.dao.MemberDao;
import com.ddg.meituan.member.entity.MemberEntity;
import com.ddg.meituan.member.service.MemberService;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.naming.AuthenticationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Resource
    private MemberDao memberDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberLoginLogService memberLoginLogService;

    @Override
    public PageUtils queryPage(PageParam param) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public MemberEntity getOneByPhone(String phone) {
        return getOne(Wrappers.<MemberEntity>lambdaQuery().eq(MemberEntity::getMobile, phone));
    }

    @Override
    public CommonResult<Long> register(MemberRegisterVo memberRegisterVo) throws MeituanSysException {
        MemberEntity entity = registerMember(memberRegisterVo);
        return CommonResult.success(entity.getId());
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
    public MemberRegisterVo login(MemberRegisterVo memberRegisterVo) throws MeituanSysException {
        String phone = memberRegisterVo.getUserName();
        String phoneCode = memberRegisterVo.getPhoneCode();

        if (!StringUtils.isEmpty(phoneCode)) {
            return loginByVerificationCode(memberRegisterVo, phone, phoneCode);
        }

        MemberEntity entity = getOneByPhone(phone);
        if (entity == null || StringUtils.isEmpty(entity.getPassword())) {
            throw new MeituanLoginException("手机号没有被进行注册");
        }

        return loginByPassword(memberRegisterVo, entity);
    }

    @Override
    public UserDto loadUserByUsername(String username, String code) throws MeituanSysException {
        String phoneCodeFromRedis = null;
        if(!StringUtils.isEmpty(code)){
            phoneCodeFromRedis = getPhoneCodeFromRedis(username, code);

        }

        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(username != null, "mobile", username);
        MemberEntity memberEntity = memberDao.selectOne(wrapper);


        return buildUserDto(memberEntity, code, phoneCodeFromRedis);
    }

    private UserDto buildUserDto(MemberEntity memberEntity, String code, String phoneCodeFromRedis) {
        if(memberEntity == null){
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUsername(memberEntity.getMobile());
        userDto.setStatus(memberEntity.getStatus());
        if(!StringUtils.isEmpty(code)){
            userDto.setPassword(passwordEncoder.encode(phoneCodeFromRedis));
        }else{
            userDto.setPassword(memberEntity.getPassword());
        }
        userDto.setClientId(AuthConstant.PORTAL_CLIENT_ID);
        userDto.setId(memberEntity.getId());
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDto;
    }


    /**
     * 使用手机号和密码进行登录
     */
    private MemberRegisterVo loginByPassword(MemberRegisterVo memberRegisterVo, MemberEntity entity) {
        String pwd = entity.getPassword();
        if (!passwordEncoder.matches(memberRegisterVo.getPassword(), pwd)) {
            throw new UsernameNotFoundException("账号或者是密码不正确");
        }

        memberRegisterVo.setUserName(entity.getUsername());
        String mobile = entity.getMobile();
        memberRegisterVo.setPhoneNum(mobile);
        memberRegisterVo.setPassword(pwd);

        cacheToken(memberRegisterVo, mobile);
        saveLoginLog(entity.getId());

        return memberRegisterVo;
    }

    /**
     * 缓存token，使用MD5对手机号加密
     */
    private void cacheToken(MemberRegisterVo memberRegisterVo, String mobile) {
        String token = DigestUtils.md5DigestAsHex(mobile.getBytes());
        redisTemplate.opsForHash().put(MemberConstant.REDIS_CACHE_LOGIN_USER_KEY, token,
                JSON.toJSONString(memberRegisterVo));
    }

    /**
     * 要是传入的有验证码，使用手机号和验证码进行登录的
     */
    private MemberRegisterVo loginByVerificationCode(MemberRegisterVo memberRegisterVo,  String phone,
                                      String phoneCode) {
        String phoneCodeFromRedis = getPhoneCodeFromRedis(phone, phoneCode);
        if (!phoneCode.equals(phoneCodeFromRedis)) {
            throw new MeituanLoginException("验证码失效！");
        }

        // 验证成功 能进行登录：可以将登录的用户信息存储到 redis 中 这里的username 是手机号
        // 使用手机号进行查询
        MemberEntity entity = getOneByPhone(phone);
        if (entity == null) {
            // 没有直接进行注册：
            String codeString = UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0,
                    MemberConstant.MEMBER_CODE_LENGTH);
            memberRegisterVo.setUserName(MemberConstant.MEITUAN_USERNAME_PREFIX + codeString);
            memberRegisterVo.setPhoneNum(phone);
            memberRegisterVo.setPassword(MemberConstant.DEFAULT_PASSWORD);
            entity = registerMember(memberRegisterVo);
        } else {
            memberRegisterVo.setUserName(entity.getUsername());
            memberRegisterVo.setPhoneNum(entity.getMobile());
        }

        cacheToken(memberRegisterVo, phone);
        saveLoginLog(entity.getId());

        // 将验证码删除
        redisTemplate.delete(MemberConstant.REDIS_PHONE_CODE_PREFIX + phone);
        return memberRegisterVo;
    }

    /**
     * 保存登录日志 这里使用异步的形式
     */
    private void saveLoginLog(Long memberId) {
        HttpServletRequest request = ServletContextUtils.getHttpServletRequest();
        String ipAddr = IPUtils.getIpAddr(request);
        MemberLoginLogEntity entity = new MemberLoginLogEntity()
                .setMemberId(memberId)
                .setCreateTime(new Date())
                .setIp(ipAddr)
                .setCity("unknown") // TODO 获取城市名称
                .setLoginType(UserAgentUtils.isMobileTerminal(request) ? 2 : 1);
        memberLoginLogService.save(entity);
    }

    private String getPhoneCodeFromRedis(String phone, String phoneCode) {
        String phoneCodeFromRedis = redisTemplate.opsForValue()
                .get(MemberConstant.REDIS_PHONE_CODE_PREFIX + phone);
        if (!StringUtils.isEmpty(phoneCodeFromRedis)) {
            phoneCodeFromRedis = phoneCodeFromRedis.split("_")[0];
        } else {
            if (MemberConstant.PHONE_CODE_MOCK.equals(phoneCode)) {
                phoneCodeFromRedis = phoneCode;
            }
        }
        return phoneCodeFromRedis;
    }

    private MemberEntity registerMember(MemberRegisterVo memberRegisterVo) {
        MemberEntity memberEntity = new MemberEntity();
        // 进行注册：逻辑
        String username = memberRegisterVo.getUserName();
        String phoneNum = memberRegisterVo.getPhoneNum();
        String password = memberRegisterVo.getPassword();
        // 密码进行加密
        String digestPassword = passwordEncoder.encode(password);

        // 检查用户名称 手机号是否是唯一：
        boolean isUnique = checkPhoneNum(phoneNum);
        if (!isUnique) {
            throw new MeituanSysException("手机号或者是用户名称已占用！");
        }

        memberEntity.setUsername(username);
        memberEntity.setPassword(digestPassword);
        memberEntity.setMobile(phoneNum);
        memberDao.insert(memberEntity);
        return memberEntity;
    }

}