package com.ddg.meituan.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.storage.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:33:05
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils<WareSkuEntity> queryPage(PageParam param);
}
