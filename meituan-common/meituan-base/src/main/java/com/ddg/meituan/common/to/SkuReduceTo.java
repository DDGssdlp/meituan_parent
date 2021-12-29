package com.ddg.meituan.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: DDG
 * @Date: 2020/5/12 16:28
 * @Description: 优惠卷数据传输模型
 */
@Data
public class SkuReduceTo {

    private Long skuId;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPriceTo> memberPrice;
}
