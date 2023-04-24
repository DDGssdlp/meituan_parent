package com.ddg.meituan.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddg.meituan.product.domain.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author 
 * @email 
 * @date 2021-01-30 16:45:20
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
