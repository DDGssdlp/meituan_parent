package com.ddg.meituan.product.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/5 16:34
 * @email: wangzhijie0908@gmail.com
 */
@Data
@TableName("pms_product_info")
public class ProductInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId
    private Long Id;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品介绍描述
     */
    private String productDesc;
    /**
     * 所属分类id
     */
    private Long catalogId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 默认图片
     */
    private String defaultImg;

    /**
     *  显示图片 多图
     * */
    private String images;

    /**
     * 标题
     */
    private String title;

    /**
    *  显示状态
    * */
    @TableLogic
    private Integer showStatus;

    /**
     *  上架状态
     * */

    private Integer isPublish;
    /**
     * 副标题
     */
    private String subtitle;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 销量
     */
    private Long saleCount;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
