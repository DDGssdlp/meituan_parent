package com.ddg.meituan.product.param;

import com.ddg.meituan.base.api.PageParam;
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
public class SpuInfoParam extends PageParam {

    private Integer status;

    private Long categoryId;

    private Long brandId;
}
