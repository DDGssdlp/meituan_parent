package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.product.entity.SkuInfoEntity;
import com.ddg.meituan.product.param.SkuInfoParam;

/**
 * sku信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils<SkuInfoEntity> queryPage(PageParam param);

    /**
     *  获取skuInfo 信息 分页返回
     * @param param
     * @return
     */
    PageUtils<SkuInfoEntity> querySkuInfoByParam(SkuInfoParam param);

}

