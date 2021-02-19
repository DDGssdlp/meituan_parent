package com.ddg.meituan.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: DDG
 * @Date: 2020/5/12 16:01
 * @Description: 数据传输模型：
 */
@Data
public class SpuBoundsTo {

    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
