package com.ddg.meituan.authserver.controlleradvice;


import com.ddg.meituan.common.api.CommonResult;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ExceptionHandler(InvalidGrantException.class)
    public CommonResult<Object> handleInvalidGrantException(InvalidGrantException e) {
        return CommonResult.failed(e.getMessage());
    }

    /**
     * 账户异常(禁用、锁定、过期)
     *
     * @param e
     * @return
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public CommonResult<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return CommonResult.failed(e.getMessage());
    }
}
