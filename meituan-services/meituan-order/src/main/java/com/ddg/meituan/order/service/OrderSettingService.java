package com.ddg.meituan.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.order.entity.OrderSettingEntity;

/**
 * 订单配置信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:36:27
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils<OrderSettingEntity> queryPage(PageParam param);
}

