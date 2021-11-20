package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.product.entity.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author 
 * @email 
 * @date 2021-01-30 17:44:19
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(PageParam param);

    void updateStatus(BrandEntity brandEntity);

    List<BrandEntity> getByBrandIds(List<Long> ids);

    PageUtils queryBrandListPage(PageParam param);
}

