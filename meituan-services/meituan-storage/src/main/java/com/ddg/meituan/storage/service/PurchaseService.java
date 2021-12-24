package com.ddg.meituan.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.storage.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:33:05
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils<PurchaseEntity> queryPage(PageParam param);
}

