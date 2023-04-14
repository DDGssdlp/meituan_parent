package com.ddg.meituan.base.exception;

/**
 * Description: 自定义异常
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/11/22 11:15
 * @email: wangzhijie0908@gmail.com
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
