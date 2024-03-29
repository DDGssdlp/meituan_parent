package com.ddg.meituan.product.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/6 16:35
 * @email: wangzhijie0908@gmail.com
 */
@Data
public class BrandListVo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long brandId;

    private String name;

    private Integer rate;

    private String img;

    private String type;

    private String addr;

    private String status;

    private Integer comment;

    private String description;

    private BigDecimal price;
}
