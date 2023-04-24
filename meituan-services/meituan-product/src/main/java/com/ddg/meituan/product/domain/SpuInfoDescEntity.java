package com.ddg.meituan.product.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * spu信息介绍
 * 
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
@Data
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	/**
	 * 商品id
	 */

	private Long spuId;
	/**
	 * 商品介绍
	 */
	private String description;

}
