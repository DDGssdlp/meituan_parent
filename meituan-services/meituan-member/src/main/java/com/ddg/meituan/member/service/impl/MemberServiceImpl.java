package com.ddg.meituan.member.service.impl;

import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.domain.UserDto;
import com.ddg.meituan.base.exception.MeituanSysException;
import com.ddg.meituan.base.utils.*;

import com.ddg.meituan.member.entity.MemberLoginLogEntity;
import com.ddg.meituan.member.enums.MemberEnum;
import com.ddg.meituan.member.service.MemberLoginLogService;
import com.ddg.meituan.member.vo.MemberRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ddg.meituan.member.dao.MemberDao;
import com.ddg.meituan.member.entity.MemberEntity;
import com.ddg.meituan.member.service.MemberService;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * @author DELL
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {


    private final MemberDao memberDao;

    private final StringRedisTemplate redisTemplate;

    private final PasswordEncoder passwordEncoder;

    private final MemberLoginLogService memberLoginLogService;

    private static final String USERNAME_PRE = "meituan_";

    private static final int RANDOM_USERNAME_LENGTH = 10;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao, StringRedisTemplate redisTemplate, PasswordEncoder passwordEncoder, MemberLoginLogService memberLoginLogService) {
        this.memberDao = memberDao;
        this.redisTemplate = redisTemplate;
        this.passwordEncoder = passwordEncoder;
        this.memberLoginLogService = memberLoginLogService;
    }

    @Override
    public PageUtils<MemberEntity>  queryPage(PageParam param) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }


    @Override
    public CommonResult<Long> register(MemberRegisterVo memberRegisterVo) throws MeituanSysException {
        MemberEntity entity = registerMember(memberRegisterVo);
        return CommonResult.success(entity.getId());
    }

    @Override
    public boolean checkPhoneNum(String phoneNum) {
        if(MemberConstant.PHONE_NUMBER_MOCK.equals(phoneNum)){
            return true;
        }else{
            QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
            wrapper.eq(MemberConstant.MOBILE_COLUMN, phoneNum);
            Integer count = memberDao.selectCount(wrapper);
            return count == 0;
        }

    }



    @Override
    public CommonResult<UserDto> loadUserByUsername(String username, String code) throws MeituanSysException {
        String phoneCodeFromRedis = null;

        if(!StringUtils.isEmpty(code)){
            phoneCodeFromRedis = getPhoneCodeFromRedis(username, code);
            if(!code.equals(phoneCodeFromRedis)){
                return CommonResult.failed("验证码失效！");
            }else{
                if(!MemberConstant.PHONE_CODE_MOCK.equals(code)){
                    redisTemplate.delete(MemberConstant.REDIS_PHONE_CODE_PREFIX + username);
                }
            }
        }

        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(username != null, MemberConstant.MOBILE_COLUMN, username);
                //.or().eq(MemberConstant.USERNAME_COLUMN, username);
        MemberEntity memberEntity = memberDao.selectOne(wrapper);

        return buildUserDto(memberEntity, username, code, phoneCodeFromRedis);
    }

    private CommonResult<UserDto> buildUserDto(MemberEntity memberEntity, String username, String code, String phoneCodeFromRedis) {
        if(memberEntity == null){
           if((memberEntity = register(username)) == null){
               return CommonResult.failed("手机号已占用 请登录!");
           }
        }

        UserDto userDto = new UserDto();
        userDto.setUsername(memberEntity.getUsername());
        userDto.setStatus(memberEntity.getStatus());
        if(!StringUtils.isEmpty(code)){
            userDto.setPassword(passwordEncoder.encode(phoneCodeFromRedis));
        }else{
            userDto.setPassword(memberEntity.getPassword());
        }
        userDto.setClientId(AuthConstant.PORTAL_CLIENT_ID);
        userDto.setId(memberEntity.getId());

        return CommonResult.success(userDto);
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
                // TODO 获取城市名称
                .setCity("unknown")
                .setLoginType(UserAgentUtils.isMobileTerminal(request) ? 2 : 1);
        memberLoginLogService.save(entity);
    }

    private String getPhoneCodeFromRedis(String phone, String phoneCode) {

        if (MemberConstant.PHONE_CODE_MOCK.equals(phoneCode)) {
            return MemberConstant.PHONE_CODE_MOCK;
        }
        String phoneCodeFromRedis = redisTemplate.opsForValue()
                .get(MemberConstant.REDIS_PHONE_CODE_PREFIX + phone);
        if (!StringUtils.isEmpty(phoneCodeFromRedis)) {
            phoneCodeFromRedis = phoneCodeFromRedis.split("_")[0];
        }
        return phoneCodeFromRedis;
    }

    private MemberEntity registerMember(MemberRegisterVo memberRegisterVo) {
        MemberEntity memberEntity = new MemberEntity();
        // 进行注册：逻辑
        String phoneNum = memberRegisterVo.getPhoneNum();
        String password = memberRegisterVo.getPassword();
        String username = memberRegisterVo.getUserName();
        String code = memberRegisterVo.getPhoneCode();
        if(!StringUtils.isEmpty(code)){
           String phoneCodeFromRedis = getPhoneCodeFromRedis(username, code);
            if(!code.equals(phoneCodeFromRedis)){
                throw new MeituanSysException("验证码失效！");
            }else{
                redisTemplate.delete(MemberConstant.REDIS_PHONE_CODE_PREFIX + username);
            }
        }
        // 检查用户名称 手机号是否是唯一：
        boolean checked = checkPhoneNum(phoneNum);
        if (!checked) {
            throw new MeituanSysException("手机号或者是用户名称已占用！");
        }
        // 密码进行加密
        String digestPassword = passwordEncoder.encode(password);

        if(StringUtils.isEmpty(username)){
            String randomUsername = UsernameUtils.getStringRandom(RANDOM_USERNAME_LENGTH);
            memberEntity.setUsername(USERNAME_PRE + randomUsername);
        }else{
            memberEntity.setUsername(username);
        }

        memberEntity.setPassword(digestPassword);
        memberEntity.setMobile(phoneNum);
        memberDao.insert(memberEntity);
        return memberEntity;
    }

    private MemberEntity register(String username) {

        MemberEntity memberEntity = null;
        // 进行注册：逻辑
        // 检查用户名称 手机号是否是唯一：
        boolean isUnique = checkPhoneNum(username);
        if (isUnique) {
            memberEntity = new MemberEntity();
            String randomUsername = UsernameUtils.getStringRandom(RANDOM_USERNAME_LENGTH);
            memberEntity.setUsername(USERNAME_PRE + randomUsername);
            memberEntity.setMobile(username);
            memberEntity.setPassword(passwordEncoder.encode(username));
            memberEntity.setStatus(MemberEnum.Status.UP.code);
            memberDao.insert(memberEntity);
        }
        return memberEntity;

    }

}