package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.entity.BrandEntity;
import com.ddg.meituan.product.vo.BrandListVo;

import java.util.List;

/**
 * 品牌
 *
 * @author 
 * @email 
 * @date 2021-01-30 17:44:19
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils<BrandEntity> queryPage(PageParam param);

    void updateStatus(BrandEntity brandEntity);

    List<BrandEntity> getByBrandIds(List<Long> ids);

    /**
     * 获取商家的列表分页展示；
     * @param param
     * @return
     */
    PageUtils<BrandListVo> queryBrandListPage(PageParam param);
}

