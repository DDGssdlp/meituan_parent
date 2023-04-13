package com.ddg.meituan.authserver.controlleradvice;


import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.enums.Code;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
@RestControllerAdvice
public class Oauth2ExceptionHandler {

    /**
     * 用户名和密码错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(OAuth2Exception.class)
    public CommonResult<?> handleInvalidGrantException(OAuth2Exception e) {
        return CommonResult.failed(Code.UNAUTHORIZED, e.getMessage());
    }
}
