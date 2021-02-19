package com.ddg.meituan.authserver.controller;

import com.ddg.meituan.authserver.constant.AuthServerConstant;
import com.ddg.meituan.authserver.feign.ThirdPartyFeignService;
import com.ddg.meituan.authserver.service.LoginService;
import com.ddg.meituan.authserver.vo.MemberRegisterVo;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


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

    @Autowired
    private ThirdPartyFeignService thirdPartyFeignService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/send/code/{phoneNum}")
    public R sendCOde(@PathVariable("phoneNum")String phoneNum){

        return thirdPartyFeignService.getSendPhoneNum(phoneNum);

    }

    @PostMapping("/register")
    public R register(@RequestBody  @Validated MemberRegisterVo memberRegisterVo) throws MeituanSysException {
        return  loginService.register(memberRegisterVo);
    }

    @PostMapping("/login")
    public R login(@RequestBody  MemberRegisterVo memberRegisterVo) throws MeituanSysException{
        return loginService.login(memberRegisterVo);
    }

    @GetMapping("/exit")
    public R exit(@RequestParam("loginUserPhone") String phone){
        String loginUserStr = (String) redisTemplate.opsForHash().get(AuthServerConstant.REDIS_CACHE_LOGIN_USER_KEY,
                phone);

        if (!StringUtils.isEmpty(loginUserStr)){
            redisTemplate.opsForHash().delete(AuthServerConstant.REDIS_CACHE_LOGIN_USER_KEY, phone);
        }
        return R.ok();
    }



}
