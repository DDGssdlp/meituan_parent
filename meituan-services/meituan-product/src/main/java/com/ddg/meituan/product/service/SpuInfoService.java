package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.product.entity.SpuInfoEntity;
import com.ddg.meituan.product.param.SpuInfoParam;
import com.ddg.meituan.product.vo.SpuInfoVo;

/**
 * spu信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils<SpuInfoEntity> queryPage(SpuInfoParam param);

    /**
     * 保存商品
     * @param spuInfo
     */
    void saveSpuInfoVo(SpuInfoVo spuInfo);
}

