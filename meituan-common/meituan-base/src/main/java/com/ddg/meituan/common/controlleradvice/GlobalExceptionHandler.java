package com.ddg.meituan.common.controlleradvice;


import com.ddg.meituan.common.api.Code;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.exception.MeituanLoginException;
import com.ddg.meituan.common.exception.MeituanSysException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: DDG
 * @Date: 2020/5/10 12:11
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MeituanSysException.class)
    public CommonResult<Object> validMeituanSysExceptionHandel(MeituanSysException e){
        log.error("出现了异常: {} , 出现的原因是: {}", e.getClass().getSimpleName(), e.getMessage());

        return CommonResult.failed(e.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Object> validMethodArgumentNotValidExceptionHandel(MethodArgumentNotValidException e){

        BindingResult bindingResult = e.getBindingResult();
        String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        log.error("出现了异常:{} , 出现的原因是{}", e.getClass().getSimpleName(), e.getMessage());
        return CommonResult.validateFailed(message);

    }
    @ExceptionHandler(Exception.class)
    public CommonResult<Object> validExceptionHandle(Exception e){
        log.error("出现了异常:{} , 出现的原因是{}", e.getClass().getSimpleName(), e.getMessage());
        return CommonResult.failed(Code.UN_NONE_EXCEPTION, e.getMessage());
    }



}
