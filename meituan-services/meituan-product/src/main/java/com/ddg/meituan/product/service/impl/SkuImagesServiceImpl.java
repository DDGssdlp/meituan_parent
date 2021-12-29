package com.ddg.meituan.product.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.Query;

import com.ddg.meituan.product.dao.SkuImagesDao;
import com.ddg.meituan.product.entity.SkuImagesEntity;
import com.ddg.meituan.product.service.SkuImagesService;


@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {

    @Override
    public PageUtils<SkuImagesEntity> queryPage(PageParam param) {
        IPage<SkuImagesEntity> page = this.page(
                new Query<SkuImagesEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

}