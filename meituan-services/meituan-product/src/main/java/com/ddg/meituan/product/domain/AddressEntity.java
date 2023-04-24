package com.ddg.meituan.product.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 地址表
 * 
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-31 12:09:54
 */
@Data
@TableName("pms_address")
public class AddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Integer addressId;
	/**
	 * 详细地址
	 */
	private String addressName;
	/**
	 * 洲id
	 */
	private Integer continentId;
	/**
	 * 洲名称
	 */
	private String continentName;
	/**
	 * 国家id
	 */
	private Integer countryId;
	/**
	 * 国家名称
	 */
	private String countryName;
	/**
	 * 州、省id
	 */
	private Integer stateId;
	/**
	 * 省 国标编码
	 */
	private String stateCodeFull;
	/**
	 * 州、省名称
	 */
	private String stateName;
	/**
	 * 市id
	 */
	private Integer cityId;
	/**
	 * 城市 国标编码
	 */
	private String cityCodeFull;
	/**
	 * 市名称
	 */
	private String cityName;
	/**
	 * 区id
	 */
	private Integer regionId;
	/**
	 * 区 国标编码
	 */
	private String regionCodeFull;
	/**
	 * 区名称
	 */
	private String regionName;
	/**
	 * 纬度
	 */
	private BigDecimal latitude;
	/**
	 * 经度
	 */
	private BigDecimal longitude;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 逻辑删除
	 */
	private Integer deletedStatus;

}
