package com.ddg.meituan.common.utils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

// 定义统一的返回的类型：
@Data
public class CommonResult<T> {
    @ApiModelProperty(value = "是否是成功")
    private Boolean success;
    @ApiModelProperty("返回的状态码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("返回的数据")
    private T data;

    private CommonResult() {

    }

    //成功的静态方法 ：
    public static CommonResult success() {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(true);
        commonResult.setCode(ResultCode.SUCCESS_CODE.getCode());
        commonResult.setMessage(ResultCode.SUCCESS_CODE.getDesc());
        return commonResult;
    }

    // 失败的静态方法：
    public static CommonResult error() {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(false);
        commonResult.setCode(ResultCode.SERVICE_ERROR.getCode());
        commonResult.setMessage(ResultCode.SERVICE_ERROR.getDesc());
        return commonResult;
    }

    // 下面的方式是为了 进行链式编程
    public CommonResult<T> success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public CommonResult<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public CommonResult<T> code(Integer code) {
        this.setCode(code);
        return this;

    }

    public CommonResult<T> data(T t) {
        this.setData(t);
        return this;
    }


}
