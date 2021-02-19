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
public enum ProductEnum {

    PUBLISH(1),
    UP(2),
    DELETED(0);

    private Integer state;

    ProductEnum(Integer state){
        this.state = state;
    }

    public Integer getState() {
        return state;
    }
}
