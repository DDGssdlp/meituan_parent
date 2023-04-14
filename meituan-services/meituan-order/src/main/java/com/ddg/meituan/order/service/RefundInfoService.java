package com.ddg.meituan.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.order.entity.RefundInfoEntity;

/**
 * 退款信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:36:27
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils<RefundInfoEntity> queryPage(PageParam param);
}

