package com.ddg.meituan.common.exception;

/**
 * @Author: DDG
 * @Date: 2020/5/27 10:53
 * @Description:
 */
public class MeituanSysException extends RuntimeException {

    private String message;

    public MeituanSysException() {
    }

    public MeituanSysException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
