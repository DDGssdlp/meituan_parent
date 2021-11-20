package com.ddg.meituan.common.controlleradvice;


import com.ddg.meituan.common.api.Code;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.exception.MeituanLoginException;
import com.ddg.meituan.common.exception.MeituanSysException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
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
    public CommonResult<Object> validMeituanSysExceptionHandel(Exception e){
        if (e instanceof MeituanLoginException) {
            return CommonResult.failed(Code.UNAUTHORIZED);
        }
        if (e instanceof MeituanSysException){
            MeituanSysException exception = (MeituanSysException) e;
            return CommonResult.failed(Code.SERVER_ERROR);
        }
        log.error("出现了异常: {} , 出现的原因是: {}", e.getClass().getSimpleName(), e.getMessage());
        return CommonResult.failed(Code.UN_NONE_EXCEPTION);
    }

//    @ExceptionHandler(RRException.class)
//    public CommonResult<Object> rrExceptionHandler(Exception e){
//        if (e instanceof RRException){
//            return CommonResult.failed(Code.RR_EXCEPTION);
//        }
//        log.error("出现了异常:{} , 出现的原因是{}", e.getClass().getSimpleName(), e.getMessage());
//        return CommonResult.failed(Code.UN_NONE_EXCEPTION.getValue(), Code.VALID_EXCEPTION.getHintMessage(),
//                e.getMessage());
//    }




    @ExceptionHandler(Exception.class)
    public CommonResult<Object> validExceptionHandle(Exception e){
        if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = exception.getBindingResult();
            Map<String, String> exceptionMap = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,
                    FieldError::getDefaultMessage, (oldVal, currVal) -> oldVal));

            return CommonResult.failed(Code.VALID_EXCEPTION, exceptionMap);
        }
        log.error("出现了异常:{} , 出现的原因是{}", e.getClass().getSimpleName(), e.getMessage());
        return CommonResult.failed(Code.UN_NONE_EXCEPTION.getValue(), Code.UN_NONE_EXCEPTION.getHintMessage(),
                e.getMessage());

    }

}
