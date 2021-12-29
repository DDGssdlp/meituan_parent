package com.ddg.meituan.product.vo;

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
 * @date 2021/12/29 09:29
 * @email: wangzhijie0908@gmail.com
 */
@Data
public class SkuInfoVo {

    private List<AttrVo> attr;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private List<Images> images;
    private List<String> description;
    private Integer fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer priceStatus;
    private List<MemberPrice> memberPrice;



    @Data
    public static class Images {
        private String imgUrl;
        private int defaultImg;
    }

    @Data
    public static class MemberPrice {
        private Long id;
        private String name;
        private BigDecimal price;
    }

}
