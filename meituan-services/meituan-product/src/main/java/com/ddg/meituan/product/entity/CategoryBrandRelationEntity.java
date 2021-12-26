package com.ddg.meituan.product.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 品牌分类关联
 * 
 * @author
 * @email
 * @date 2020-05-31 17:06:04
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 品牌id
	 */
	@NotNull(message = "品牌id 不能为空")
	private Long brandId;
	/**
	 * 分类id
	 */
	@NotNull(message = "分类id 不能为空")
	private Long categoryId;
	/**
	 * 
	 */
	private String brandName;
	/**
	 * 
	 */
	private String categoryName;

	@TableLogic
	private Integer deletedStatus;

	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}
