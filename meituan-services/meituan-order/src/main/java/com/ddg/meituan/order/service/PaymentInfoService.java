package com.ddg.meituan.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.order.entity.PaymentInfoEntity;

/**
 * 支付信息表
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:36:27
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageUtils<PaymentInfoEntity> queryPage(PageParam param);
}

