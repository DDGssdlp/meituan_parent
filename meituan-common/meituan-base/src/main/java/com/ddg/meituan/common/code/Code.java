package com.ddg.meituan.common.code;


/**
 * Description: 动态枚举配置
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
public enum  Code {

    NO_PROBLEM("00000", "一切 OK");

    Code(String value, String hintMessage) {
        this.value = value;
        this.hintMessage = hintMessage;
    }

    private final String value;
    private final String hintMessage;

    public String getValue() {
        return value;
    }

    public String getHintMessage() {
        return hintMessage;
    }
}
