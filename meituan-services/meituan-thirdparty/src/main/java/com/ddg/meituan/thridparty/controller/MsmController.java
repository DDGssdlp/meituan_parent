package com.ddg.meituan.thridparty.controller;

import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.thridparty.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public MsmController(MsmService msmService) {
        this.msmService = msmService;
    }


    @GetMapping("/send/{phoneNum}")
    @ApiOperation("通过手机号发送短信验证码")
    public CommonResult<String> getSendPhoneNum(@PathVariable("phoneNum") String phoneNum) {
        return msmService.sendCode(phoneNum);
    }

}
