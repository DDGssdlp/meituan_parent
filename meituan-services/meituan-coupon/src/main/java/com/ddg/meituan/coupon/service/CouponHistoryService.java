package com.ddg.meituan.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.coupon.entity.CouponHistoryEntity;

/**
 * 优惠券领取历史记录
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:17
 */
public interface CouponHistoryService extends IService<CouponHistoryEntity> {

    PageUtils<CouponHistoryEntity> queryPage(PageParam param);
}

