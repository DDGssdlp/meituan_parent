package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.PageUtils;

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

    PageUtils<CategoryEntity> queryPage(PageParam param);

    List<CategoryEntity> getListWithTree();

    void changeStatus(CategoryEntity categoryEntity);

    PageUtils<CategoryEntity> queryPageById(PageParam param);

    List<CategoryEntity> getParentList();

    /**
     * 获取当前的分类ID 所在的路径
     * @param categoryId
     * @param isSelf
     * @return
     */
    Long[] findCategoryPath(Long categoryId, boolean isSelf);
}

