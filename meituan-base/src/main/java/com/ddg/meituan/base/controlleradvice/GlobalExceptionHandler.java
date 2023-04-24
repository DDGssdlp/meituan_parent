package com.ddg.meituan.base.controlleradvice;


import com.ddg.meituan.base.enums.Code;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.exception.MeituanSysException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Objects;

/**
 * Description: 全局异常处理
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/4/27 12:27
 * @email: wangzhijie0908@gmail.com
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MeituanSysException.class)
    public CommonResult<?> validMeituanSysExceptionHandel(MeituanSysException e){
        log.error("出现了异常: {} , 出现的原因是: {}", e.getClass().getSimpleName(), e.getMessage());
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<?> validMethodArgumentNotValidExceptionHandel(MethodArgumentNotValidException e){

        BindingResult bindingResult = e.getBindingResult();
        String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        log.error("出现了异常:{} , 出现的原因是{}", e.getClass().getSimpleName(), e.getMessage());
        return CommonResult.validateFailed(message);

    }
    @ExceptionHandler(Exception.class)
    public CommonResult<?> validExceptionHandle(Exception e){
        log.error("出现了异常:{} , 出现的原因是{}", e.getClass().getSimpleName(), e.getMessage());
        return CommonResult.failed(Code.UN_NONE_EXCEPTION, e.getMessage());
    }



}
