package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;

import com.ddg.meituan.product.entity.CategoryEntity;

import java.util.List;


/**
 * 商品三级分类
 *
 * @author 
 * @email 
 * @date 2021-01-29 11:22:05
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(PageParam param);

    List<CategoryEntity> getListWithTree();

    void changeStatus(CategoryEntity categoryEntity);

    PageUtils queryPageById(PageParam param);

    List<CategoryEntity> getParentList();
}

