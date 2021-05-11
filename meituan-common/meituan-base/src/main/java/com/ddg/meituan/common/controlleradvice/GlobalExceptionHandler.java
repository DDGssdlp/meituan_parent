package com.ddg.meituan.common.controlleradvice;


import com.ddg.meituan.common.exception.MeituanCodeEnum;
import com.ddg.meituan.common.exception.MeituanLoginException;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.R;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
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
    public R validMeituanSysExceptionHandel(Exception e){
        if (e instanceof MeituanLoginException) {
            return R.error().put("login", e.getMessage());
        }
        if (e instanceof MeituanSysException){
            MeituanSysException exception = (MeituanSysException) e;
            return R.error(MeituanCodeEnum.MEITUAN_EXCEPTION.getCode(), exception.getMessage());
        }
        log.error("出现了异常:{} , 出现的原因是{}", e.getClass().getSimpleName(), e.getMessage());
        return R.error(MeituanCodeEnum.UN_NONE_EXCEPTION.getCode(), MeituanCodeEnum.UN_NONE_EXCEPTION.getMessage());
    }




    @ExceptionHandler(Exception.class)
    public R validExceptionHandle(Exception e){
        if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = exception.getBindingResult();
            Map<String, String> exceptionMap = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,
                    FieldError::getDefaultMessage, (oldVal, currVal) -> oldVal));

            return R.error(MeituanCodeEnum.VALID_EXCEPTION.getCode(), MeituanCodeEnum.VALID_EXCEPTION.getMessage())
                    .put("data", exceptionMap);
        }
        log.error("出现了异常:{} , 出现的原因是{}", e.getClass().getSimpleName(), e.getMessage());
        return R.error(MeituanCodeEnum.UN_NONE_EXCEPTION.getCode(), MeituanCodeEnum.UN_NONE_EXCEPTION.getMessage()).put("data", e.getMessage());

    }

}
