package com.ddg.meituan.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.coupon.entity.SpuBoundsEntity;

/**
 * 商品spu积分设置
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils<SpuBoundsEntity> queryPage(PageParam param);
}

