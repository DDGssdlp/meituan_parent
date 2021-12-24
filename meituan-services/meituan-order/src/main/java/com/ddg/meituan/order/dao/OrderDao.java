package com.ddg.meituan.order.dao;

import com.ddg.meituan.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:36:27
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
