package com.ddg.meituan.product.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.product.entity.BrandEntity;
import com.ddg.meituan.product.entity.CategoryBrandRelationEntity;
import com.ddg.meituan.product.vo.BrandVo;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author
 * @email
 * @date 2020-05-31 17:06:04
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

	void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

	void updateBrand(Long brandId, String name);

	void updateCategory(Long catId, String name);


	List<BrandVo> getBrandsByCatId(Long catId);


}

