package com.ddg.meituan.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.coupon.entity.HomeAdvEntity;

/**
 * 首页轮播广告
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

    PageUtils<HomeAdvEntity> queryPage(PageParam param);
}

