package com.ddg.meituan.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.coupon.entity.MemberPriceEntity;

/**
 * 商品会员价格
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils<MemberPriceEntity> queryPage(PageParam param);
}

