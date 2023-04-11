package com.ddg.meituan.base.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: DDG
 * @Date: 2020/5/16 16:15
 * @Description:
 */
@Data
public class SkuEsModelTo {

    private Long skuId;
    private Long spuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;
    private Long saleCount;
    private Boolean hasStock;
    private Long hotScore;
    private Long brandId;
    private Long catalogId;
    private String brandName;
    private String brandImg;
    private String catalogName;

    private List<Attrs> attrs;

    @Data
    public static class Attrs{
        private Long attrId;
        private String attrName;
        private String attrValue;
    }
}
