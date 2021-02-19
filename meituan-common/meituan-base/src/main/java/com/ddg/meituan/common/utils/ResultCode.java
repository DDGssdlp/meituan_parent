package com.ddg.meituan.common.utils;

public enum ResultCode {
    // 定义状态吗
    SUCCESS_CODE(20000, "执行成功"),
    //异常 返回码范围 400 -419 业务无关异常
    INVALID_PARAM(40001,"无效参数"),
    //服务端异常
    SERVICE_ERROR(20001, "执行失败");

    private int code;
    private String desc;

    // 构造函数默认的 就是私有的
    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }





}
