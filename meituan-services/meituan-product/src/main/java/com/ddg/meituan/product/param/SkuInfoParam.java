package com.ddg.meituan.product.param;

import com.ddg.meituan.base.utils.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/28 11:01
 * @email: wangzhijie0908@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SkuInfoParam extends PageParam {


    private Long categoryId;

    private Long brandId;

    private String min;

    private String max;
}
