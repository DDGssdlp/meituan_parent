package com.ddg.meituan.authserver.service.impl;

import com.ddg.meituan.authserver.constant.AuthServerConstant;
import com.ddg.meituan.authserver.feign.MemberFeignService;
import com.ddg.meituan.authserver.service.LoginService;
import com.ddg.meituan.authserver.vo.MemberRegisterVo;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.R;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/1 10:00
 * @email: wangzhijie0908@gmail.com
 */
@Service
public class LoginServiceImpl implements LoginService {
    
    private final StringRedisTemplate redisTemplate;

    private final MemberFeignService memberFeignService;

    @Autowired
    public LoginServiceImpl(StringRedisTemplate redisTemplate, MemberFeignService memberFeignService) {
        this.redisTemplate = redisTemplate;
        this.memberFeignService = memberFeignService;
    }


    @Override
    public R register(MemberRegisterVo memberRegisterVo) throws MeituanSysException {
        // 首先获取验证码
        String phoneCode = memberRegisterVo.getPhoneCode();
        String phoneNum = memberRegisterVo.getPhoneNum();

        String phoneCodeFromRedis = redisTemplate.opsForValue().get(AuthServerConstant.REDIS_PHONE_CODE_PREFIX + phoneNum);


        if (!StringUtils.isEmpty(phoneCodeFromRedis)){
            phoneCodeFromRedis = phoneCodeFromRedis.split("_")[0];
            if (phoneCode.equals(phoneCodeFromRedis) ){
                // TODO 验证码校验通过 使用第三方进行注册
                R register = memberFeignService.register(memberRegisterVo);
                // 删除验证码：
                redisTemplate.delete(AuthServerConstant.REDIS_PHONE_CODE_PREFIX + phoneNum);
                return register;
            }else{
                // 验证码校验不通过
               throw new MeituanSysException("验证码校验不通过！");
            }
        }else{
            if (AuthServerConstant.PHONE_CODE_MOCK.equals(phoneCode)){
                // TODO 验证码校验通过 使用第三方进行注册
                R register = memberFeignService.register(memberRegisterVo);
                return register;
            }
        }
        throw new MeituanSysException("验证码过期！");
    }

    @Override
    public R login(MemberRegisterVo memberRegisterVo) throws MeituanSysException{
        // 首先获取验证码 使用第三方进行登录
        return memberFeignService.login(memberRegisterVo);

    }
}
