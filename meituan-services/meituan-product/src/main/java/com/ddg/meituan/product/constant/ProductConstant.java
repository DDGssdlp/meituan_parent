package com.ddg.meituan.product.constant;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * Description: 商品服务所使用的常量
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/29 14:01
 * @email: wangzhijie0908@gmail.com
 */
public interface ProductConstant {

    Integer CAT_LEVEL_ONE = 1;
    Integer CAT_LEVEL_TWO = 2;
    Integer CAT_LEVEL_THREE = 3;
    Integer MAX_FATHER_LENGTH = 16;
    Integer MAX_LEVEL3_COUNT = 15;
    Integer MAX_LEVEL2_COUNT = 5;
    Integer SHOW_STATUS = 1;
    Integer UN_SHOW_STATUS = 0;
    String CART_ID = "cartId";
    String PARENT_CART_ID = "parent_cid";

    String PRODUCT_ID = "id";
    String CATEGORY_NAME = "name";
    String CART_LEVEL = "cat_level";

    Long ROOT_CID = 0L;

    String DEFAULT_IMG = "https://edu-ddg.oss-cn-beijing.aliyuncs.com/2021/02/06/7a940e9b1b7c40d1a9cf2ab7c90eafda1.jpg";
}
