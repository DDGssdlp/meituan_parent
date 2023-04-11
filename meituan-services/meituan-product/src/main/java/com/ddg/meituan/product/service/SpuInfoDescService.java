package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.product.entity.SpuInfoDescEntity;

/**
 * spu信息介绍
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils<SpuInfoDescEntity> queryPage(PageParam param);

    void saveSpuInfoDesc(SpuInfoDescEntity spuInfoDescEntity);
}

