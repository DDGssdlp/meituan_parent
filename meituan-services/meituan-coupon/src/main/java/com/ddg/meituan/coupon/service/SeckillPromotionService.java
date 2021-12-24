package com.ddg.meituan.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * 秒杀活动
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils<SeckillPromotionEntity> queryPage(PageParam param);
}
