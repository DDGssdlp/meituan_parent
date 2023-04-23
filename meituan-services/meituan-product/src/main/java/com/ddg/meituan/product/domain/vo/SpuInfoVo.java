package com.ddg.meituan.product.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/29 09:26
 * @email: wangzhijie0908@gmail.com
 */
@Data
public class SpuInfoVo {

    private String spuName;
    private String spuDescription;
    private Long categoryId;
    private Long brandId;
    private BigDecimal weight;
    private Integer publishStatus;
    private List<String> description;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttr> baseAttrs;
    private List<SkuInfoVo> skus;


    @Data
    public static class Bounds {
        private BigDecimal buyBounds;
        private BigDecimal growBounds;

    }

    @Data
    public static class BaseAttr {
        private Long attrId;
        private String attrValues;
        private Integer showDesc;
    }


}