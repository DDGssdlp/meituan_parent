package com.ddg.meituan.product.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import com.ddg.meituan.common.annotation.vaildator.ListValue;
import com.ddg.meituan.common.annotation.validgroup.services.AddGroup;
import com.ddg.meituan.common.annotation.validgroup.services.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 商品三级分类
 * 
 * @author 
 * @email 
 * @date 2021-01-30 16:45:20
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@TableId(type = IdType.AUTO)
	@NotNull(message = "修改必须指定分类id", groups = {UpdateGroup.class})
	@Null(message = "新增不能指定分类id", groups = {AddGroup.class})
	private Long catId;
	/**
	 * 分类名称
	 */
	@NotBlank(message = "分类名称必须提交", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 父分类id
	 */
	@NotNull(message = "父分类id必须提交", groups = {AddGroup.class, UpdateGroup.class})
	private Long parentCid;
	/**
	 * 层级
	 */
	@NotNull(message = "层级必须提交", groups = {AddGroup.class, UpdateGroup.class})
	private Integer catLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	@ListValue(values = {0, 1}, message = "显示状态必须是0/1", groups = {AddGroup.class, UpdateGroup.class})
	private Integer showStatus;
	/**
	 * 逻辑删除 1为删除 0删除
	 */
	@TableLogic
	@Size(min = 0, max = 1, message = "删除状态必须是0/1", groups = {AddGroup.class, UpdateGroup.class})
	private Integer deletedStatus;
	/**
	 * 排序
	 */
	@Min(value = 0, message = "排序字段碧玺是大于等于零的整数", groups = {AddGroup.class, UpdateGroup.class})
	private Integer sort;
	/**
	 * 图标地址
	 */
	@URL(message = "图片地址必须是URL格式", groups = {AddGroup.class, UpdateGroup.class})
	private String icon;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableField(exist = false)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<CategoryEntity> children;

}
