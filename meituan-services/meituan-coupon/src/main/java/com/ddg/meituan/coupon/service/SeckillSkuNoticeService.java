package com.ddg.meituan.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.coupon.entity.SeckillSkuNoticeEntity;

/**
 * 秒杀商品通知订阅
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
public interface SeckillSkuNoticeService extends IService<SeckillSkuNoticeEntity> {

    PageUtils<SeckillSkuNoticeEntity> queryPage(PageParam param);
}

