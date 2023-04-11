package com.ddg.meituan.authserver.controller;

import com.ddg.meituan.authserver.feign.ThirdPartyFeignService;
import com.ddg.meituan.authserver.service.LoginService;
import com.ddg.meituan.authserver.vo.MemberRegisterVo;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.exception.MeituanSysException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/31 13:37
 * @email: wangzhijie0908@gmail.com
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final ThirdPartyFeignService thirdPartyFeignService;
    private final LoginService loginService;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public LoginController(ThirdPartyFeignService thirdPartyFeignService, LoginService loginService, StringRedisTemplate redisTemplate) {
        this.thirdPartyFeignService = thirdPartyFeignService;
        this.loginService = loginService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/send/code/{phoneNum}")
    public CommonResult sendCode(@PathVariable("phoneNum")String phoneNum){

        return thirdPartyFeignService.getSendPhoneNum(phoneNum);

    }

    @PostMapping("/registerUser")
    public CommonResult register(@RequestBody  @Validated MemberRegisterVo memberRegisterVo) throws MeituanSysException {
        return loginService.register(memberRegisterVo);
    }

    @PostMapping("/login")
    public CommonResult login(@RequestBody  MemberRegisterVo memberRegisterVo) throws MeituanSysException{
        return loginService.login(memberRegisterVo);
    }

    @GetMapping("/exit")
    @Deprecated
    public CommonResult<?> exit(@RequestParam("loginUserPhone") String phone){

        return CommonResult.success();
    }



}
