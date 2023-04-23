package com.ddg.meituan.product.enums;

/**
 * Description:  商品上架状态显示：
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/6 12:22
 * @email: wangzhijie0908@gmail.com
 */

public interface ProductEnum{
    enum StatusEnum {

        NEW_SPU(0,"新建"),
        SPU_UP(1,"商品上架"),
        SPU_DOWN(2,"商品下架"),
        ;

        private final int code;
        private final String desc;

        private static final String STATUS_DESC = "上架状态枚举描述 0 新建 1 商品上架 2 商品下架";

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return desc;
        }

        StatusEnum(int code, String msg) {
            this.code = code;
            this.desc = msg;
        }
    }

    enum AttrType{
        BASE(1, "base"),
        SALE(0, "sale")
        ;

        private int code;

        private String desc;

        private static final String STATUS_DESC = "属性类型状态描述 1 base  0 sale";

        public String getMsg() {
            return desc;
        }

        public int getCode() {
            return code;
        }

        AttrType(int code, String msg) {
            this.code = code;
            this.desc = msg;
        }
    }
}

