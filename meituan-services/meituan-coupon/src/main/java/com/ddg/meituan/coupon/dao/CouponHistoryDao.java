package com.ddg.meituan.coupon.dao;

import com.ddg.meituan.coupon.entity.CouponHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 * 
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:17
 */
@Mapper
public interface CouponHistoryDao extends BaseMapper<CouponHistoryEntity> {
	
}
