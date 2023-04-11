package com.ddg.meituan.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.order.entity.OrderEntity;

/**
 * 订单
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:36:27
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils<OrderEntity> queryPage(PageParam param);
}

