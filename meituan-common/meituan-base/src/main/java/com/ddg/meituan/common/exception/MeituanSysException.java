package com.ddg.meituan.common.exception;

/**
 * @Author: DDG
 * @Date: 2020/5/27 10:53
 * @Description:
 */
public class MeituanSysException extends RuntimeException {


    public MeituanSysException() {
    }

    public MeituanSysException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }


}
