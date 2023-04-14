package com.ddg.meituan.thridparty.controller;

import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.constant.AuthConstant;
import com.ddg.meituan.base.domain.dto.UserDto;
import com.ddg.meituan.thridparty.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
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
 * @date 2021/1/30 17:58
 * @email: wangzhijie0908@gmail.com
 */

@RestController
@RequestMapping("/msm")
@Api("短信验证")
public class MsmController {

    private final MsmService msmService;

    private final RedisTemplate<String, String> redisTemplate;

    public MsmController(MsmService msmService, RedisTemplate<String, String> redisTemplate) {
        this.msmService = msmService;
        this.redisTemplate = redisTemplate;
    }


    @GetMapping("/send/{phoneNum}")
    @ApiOperation("通过手机号发送短信验证码")
    public CommonResult<String> getSendPhoneNum(@PathVariable("phoneNum") String phoneNum) {
        return msmService.sendCode(phoneNum);
    }

    @GetMapping("/hello")
    @ApiOperation("通过手机号发送短信验证码")
    public String getHello(@RequestHeader(AuthConstant.USER_TOKEN_HEADER) UserDto userDto) {
        redisTemplate.opsForValue().set("1", "1");

        return userDto.toString();
    }
}
