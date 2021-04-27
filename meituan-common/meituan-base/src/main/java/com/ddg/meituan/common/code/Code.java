package com.ddg.meituan.common.code;


/**
 * Description: 自定义配置异常码信息：
 * # A来源于用户或前端 , B表示错误来源于后端, C表示错误来源第三方服务
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

    NO_PROBLEM("00000", "一切 OK"),
    PARAMS_ERROR("A0001", "参数错误"),
    ILLEGAL_VALUE("A0002", "参数值不合法"),
    DUPLICATE_NAME("A0003","名称重复"),
    SAVE_ERROR("B0001", "保存失败"),
    SERVER_ERROR("B0002", "服务器异常"),
    OPERATION_FAIL("B0003", "操作失败"),
    THIRD_SERVER_ERROR("C0001", "第三方服务异常");

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
