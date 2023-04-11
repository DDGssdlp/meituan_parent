package com.ddg.meituan.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.order.entity.OrderItemEntity;

/**
 * 订单项信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:36:27
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils<OrderItemEntity> queryPage(PageParam param);
}

