package com.ddg.meituan.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.order.entity.OrderReturnReasonEntity;

/**
 * 退货原因
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:36:27
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils<OrderReturnReasonEntity> queryPage(PageParam param);
}

