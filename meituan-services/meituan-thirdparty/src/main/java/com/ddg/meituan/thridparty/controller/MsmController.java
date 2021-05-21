package com.ddg.meituan.thridparty.controller;

import com.ddg.meituan.common.utils.R;
import com.ddg.meituan.common.utils.RandomUtil;
import com.ddg.meituan.thridparty.Service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
@RequestMapping("/thirdparty/msm")
public class MsmController {

    private final MsmService msmService;
    private final RedisTemplate<String, String> redisTemplate;


    @Autowired
    public MsmController(MsmService msmService, RedisTemplate<String, String> redisTemplate) {
        this.msmService = msmService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/send/{phoneNum}")
    public R getSendPhoneNum(@PathVariable("phoneNum") String phoneNum){
     return msmService.sendCode(phoneNum);
    }
}
