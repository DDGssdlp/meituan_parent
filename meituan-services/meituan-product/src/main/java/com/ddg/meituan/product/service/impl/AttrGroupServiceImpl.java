package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.Query;

import com.ddg.meituan.product.dao.AttrGroupDao;
import com.ddg.meituan.product.entity.AttrGroupEntity;
import com.ddg.meituan.product.service.AttrGroupService;

import java.util.Objects;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    private final AttrGroupDao attrGroupDao;

    private final CategoryService categoryService;

    @Autowired
    public AttrGroupServiceImpl(AttrGroupDao attrGroupDao, CategoryService categoryService) {
        this.attrGroupDao = attrGroupDao;
        this.categoryService = categoryService;
    }

    @Override
    public PageUtils<AttrGroupEntity> queryPage(PageParam param) {
        Page<AttrGroupEntity> page = new Page<>(Long.parseLong(param.getPage()), Long.parseLong(param.getLimit()));
        IPage<AttrGroupEntity> iPage = attrGroupDao.selectAttrById(page, param);
        return PageUtils.of(iPage);
    }

    @Override
    public AttrGroupEntity getInfoById(Long attrGroupId) {
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
        if(Objects.isNull(attrGroupEntity)){
            return null;
        }
        Long categoryId = attrGroupEntity.getCategoryId();
        if(categoryId != null){
            attrGroupEntity.setCategoryPath(categoryService.findCategoryPath(categoryId, true));
        }
        return attrGroupEntity;
    }

}