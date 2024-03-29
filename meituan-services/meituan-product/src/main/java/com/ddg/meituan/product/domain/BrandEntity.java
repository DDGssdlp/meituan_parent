package com.ddg.meituan.product.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ddg.meituan.base.annotation.vaildator.ListValue;
import com.ddg.meituan.base.annotation.validgroup.AddGroup;
import com.ddg.meituan.base.annotation.validgroup.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 品牌
 * 
 * @author 
 * @email 
 * @date 2021-01-30 17:44:19
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId(type = IdType.AUTO)
	@NotNull(message = "修改必须定制品牌id", groups = {UpdateGroup.class})
	@Null(message = "新增不能指定id", groups = {AddGroup.class})
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名必须提交", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(groups = {AddGroup.class})
	@URL(message = "logo必须是一个合法的URL地址", groups={AddGroup.class, UpdateGroup.class})
	private String logo;
	/**
	 * 介绍
	 */

	private String images;

	private String description;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(groups = {AddGroup.class, UpdateGroup.class})
	@ListValue(values = {0,1}, groups = {AddGroup.class, UpdateGroup.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty(groups = {AddGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(groups = {AddGroup.class})
	@Min(value = 0, message = "排序必须是一个正整数" , groups = {AddGroup.class, UpdateGroup.class})
	private Integer sort;
	/**
	 * 逻辑删除
	 */
	@TableLogic
	private Integer deletedStatus;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	/**
	 *  地址：
	 * */
	private String address;

	/**
	 * 最低价格商品维护
	 */
	private BigDecimal price;

}
