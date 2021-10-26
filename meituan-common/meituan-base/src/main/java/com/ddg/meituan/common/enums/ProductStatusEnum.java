package com.ddg.meituan.common.enums;

/**
 * @author DELL
 */

public enum ProductStatusEnum {
    NEW_SPU(0, "新建状态"),
    UP_SPU(1, "上架状态"),
    DOWN_SPU(2, "商品下架");

    private int status;

    private String message;

    ProductStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
