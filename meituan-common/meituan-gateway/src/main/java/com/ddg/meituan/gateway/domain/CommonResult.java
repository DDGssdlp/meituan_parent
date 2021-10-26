package com.ddg.meituan.gateway.domain;

/**
 * 通用返回对象
 *
 * @author macro
 * @date 2019/4/19
 */
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;

    protected CommonResult() {
    }

    protected CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(Code.NO_PROBLEM.getValue(), Code.NO_PROBLEM.getHintMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(Code.NO_PROBLEM.getValue(), message, data);
    }

    public static <T> CommonResult<T> success(){
        return new CommonResult<>(Code.NO_PROBLEM.getValue(), Code.NO_PROBLEM.getHintMessage(), null);
    }



    /**
     * 失败返回结果
     * @param value
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(String value, String message, T data){
        return new CommonResult<T>(value, message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(Code errorCode, T data) {
        return failed(errorCode.getValue(), errorCode.getHintMessage(), data);
    }


    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return failed(Code.OPERATION_FAIL.getValue(), message, null);
    }

    public static <T> CommonResult<T> failed(Code errorCode){
        return failed(errorCode.getValue(), errorCode.getHintMessage(), null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(Code.OPERATION_FAIL, null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed(T data) {
        return failed(Code.ILLEGAL_VALUE, data);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(Code.ILLEGAL_VALUE.getValue(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(Code.UNAUTHORIZED.getValue(), Code.UNAUTHORIZED.getHintMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(Code.FORBIDDEN.getValue(), Code.FORBIDDEN.getHintMessage(), data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
