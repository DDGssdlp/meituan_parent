package com.ddg.meituan.product.dao;

import com.ddg.meituan.product.domain.AddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地址表
 * 
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-31 12:09:54
 */
@Mapper
public interface AddressDao extends BaseMapper<AddressEntity> {
	
}
