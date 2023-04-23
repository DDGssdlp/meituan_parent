package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.api.Query;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.dao.SkuImagesDao;
import com.ddg.meituan.product.domain.SkuImagesEntity;
import com.ddg.meituan.product.service.SkuImagesService;
import org.springframework.stereotype.Service;


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