package com.ddg.meituan.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.Query;
import com.ddg.meituan.product.constant.BrandConstant;
import com.ddg.meituan.product.dao.BrandDao;
import com.ddg.meituan.product.dao.CategoryBrandRelationDao;
import com.ddg.meituan.product.dao.CategoryDao;
import com.ddg.meituan.product.entity.BrandEntity;
import com.ddg.meituan.product.entity.CategoryBrandRelationEntity;
import com.ddg.meituan.product.entity.CategoryEntity;
import com.ddg.meituan.product.service.BrandService;
import com.ddg.meituan.product.service.CategoryBrandRelationService;
import com.ddg.meituan.product.vo.BrandVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.util.List;
import java.util.stream.Collectors;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

	@Resource
	private BrandDao brandDao;

	@Resource
	private CategoryDao categoryDao;

	@Resource
	private CategoryBrandRelationDao categoryBrandRelationDao;

	@Resource
	private BrandService brandService;

    @Override
    public PageUtils<CategoryBrandRelationEntity> queryPage(PageParam param) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(param),
				new QueryWrapper<>()
        );
		return PageUtils.of(page);
    }

	/**
	 * 根据获取品牌id 、三级分类id查询对应的名字保存到数据库
	 */
	@Override
	public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
		if(queryCountById(categoryBrandRelation) > 0){
			throw new MeituanSysException("该分类已经被绑定了 不需要重复绑定！");
		}
    	// 获取品牌id 、三级分类id
		Long brandId = categoryBrandRelation.getBrandId();
		Long categoryId = categoryBrandRelation.getCategoryId();
		// 根据id查询详细名字

		BrandEntity brandEntity = brandDao.selectById(brandId);
		CategoryEntity categoryEntity = categoryDao.selectById(categoryId);
		categoryBrandRelation.setBrandName(brandEntity.getName());
		categoryBrandRelation.setCategoryName(categoryEntity.getName());
		categoryBrandRelationDao.insert(categoryBrandRelation);
	}

	@Override
	public void updateBrand(Long brandId, String name) {
		CategoryBrandRelationEntity entity = new CategoryBrandRelationEntity();
		entity.setBrandId(brandId);
		entity.setBrandName(name);
		// 将所有品牌id为 brandId 的进行更新
		categoryBrandRelationDao.update(entity, new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id",brandId));
	}

	@Override
	public void updateCategory(Long catId, String name) {
		categoryBrandRelationDao.updateCategory(catId, name);
	}

	@Override
	public Integer queryCountById(CategoryBrandRelationEntity categoryBrandRelation) {

		QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
		wrapper.eq("brand_id" , categoryBrandRelation.getBrandId()).and(i ->i.eq("category_id",
				categoryBrandRelation.getCategoryId()));
		return categoryBrandRelationDao.selectCount(wrapper);

	}

	/**
	 * 获取某个分类下所有品牌信息
	 */
	@Override
	public List<BrandVo> getBrandsByCatId(Long catId) {
		List<CategoryBrandRelationEntity> cateBrandList =
				categoryBrandRelationDao.selectList
						(new QueryWrapper<CategoryBrandRelationEntity>().eq(BrandConstant.CATEGORY_ID, catId));
		// 根据品牌id查询详细信息
		if (CollectionUtils.isEmpty(cateBrandList)){
			return null;
		}
		List<Long> brandIds =
				cateBrandList.stream().map(CategoryBrandRelationEntity::getBrandId).collect(Collectors.toList());
		List<BrandEntity> brandEntityList = brandService.getByBrandIds(brandIds);
		// 转成vo 返回
		List<BrandVo> collect = brandEntityList.stream().map(brandEntity -> {
			BrandVo brandVo = new BrandVo();
			brandVo.setBrandId(brandEntity.getBrandId());
			brandVo.setBrandName(brandEntity.getName());
			return brandVo;
		}).collect(Collectors.toList());


		return collect;
	}

}