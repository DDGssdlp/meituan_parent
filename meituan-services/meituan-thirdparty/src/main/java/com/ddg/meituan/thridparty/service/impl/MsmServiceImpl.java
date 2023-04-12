package com.ddg.meituan.thridparty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.thridparty.service.MsmService;
import com.ddg.meituan.thridparty.component.OSSConfigurationProperties;
import com.ddg.meituan.thridparty.constant.ThirdPartyConstant;
import com.ddg.meituan.thridparty.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
@Service
public class MsmServiceImpl implements MsmService {

    private final OSSConfigurationProperties properties;
    private final RedisTemplate<String, String> redisTemplate;
    @Autowired
    public MsmServiceImpl(OSSConfigurationProperties constantPropertiesUtils,
                          RedisTemplate<String, String> redisTemplate) {
        this.properties = constantPropertiesUtils;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public CommonResult<String> sendCode(String phoneNum){
        // 先从redis中进行获取验证码
        String code = redisTemplate.opsForValue().get(ThirdPartyConstant.REDIS_PHONE_CODE_PREFIX + phoneNum);
        boolean isSend = false;
        // 判断是否是在redis中有值
        if(StringUtils.isEmpty(code)){
            // 要是获取不到 使用阿里云进行发送
            code = RandomUtil.getSixBitRandom();
            Map<String, String> param = new HashMap<>(2);
            param.put("code", code);
            // 调用service进行发送：
            isSend = sendCodeByAly(param, phoneNum);
            if (isSend){
                // 成功发送之后 将数据放到redis中 设置超时时间 5分钟
                long l = System.currentTimeMillis();
                redisTemplate.opsForValue().set(ThirdPartyConstant.REDIS_PHONE_CODE_PREFIX + phoneNum, code+"_"+ l,
                        ThirdPartyConstant.STORE_TIME, TimeUnit.MINUTES);
            }
        }else{
            String[] s = code.split("_");
            long l = System.currentTimeMillis();
            if (l - Long.parseLong(s[1]) < ThirdPartyConstant.ONE_MIN){
                return  CommonResult.failed("60秒内不可以重复的发送验证码");
            }

        }
        return CommonResult.success();
    }

    // 使用阿里云短信服务进行发送的方法
    private boolean sendCodeByAly(Map<String, String> param, String phoneNum) {
        String id = properties.getKeyId();
        String accessKey = properties.getKeySecret();

        // 进行参数的判断：
        if (StringUtils.isEmpty(phoneNum)){
            return false;
        }
        // 使用 id 和 秘钥进行发送
        DefaultProfile profile = DefaultProfile.getProfile("default", id, accessKey);
        // 设置相关参数：
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        // 阿里云中的 签名
        request.putQueryParameter("SignName", "DDG在线教育网");
        // 模板code
        request.putQueryParameter("TemplateCode", "SMS_187756511");
        // 需要的是一种json格式 不能直接传code {"code": 123455}
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        // 最终的发送：
        boolean success = false;
        try {
            CommonResponse commonResponse = client.getCommonResponse(request);
            success = commonResponse.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return success;
    }
}
