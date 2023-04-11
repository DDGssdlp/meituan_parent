package com.ddg.meituan.thridparty.Service;

import com.ddg.meituan.base.api.CommonResult;

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
public interface MsmService {


    /**
     *  阿里云oss 使用 短信验证码发送短信接口
     * @param phoneNum
     * @return
     */
    CommonResult<String> sendCode(String phoneNum);
}
