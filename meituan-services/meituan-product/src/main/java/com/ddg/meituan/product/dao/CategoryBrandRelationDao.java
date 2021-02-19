package com.ddg.meituan.product.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddg.meituan.product.entity.CategoryBrandRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌分类关联
 * 
 * @author
 * @email
 * @date
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

	void updateCategory(@Param("catId") Long catId, @Param("name") String name);
}
