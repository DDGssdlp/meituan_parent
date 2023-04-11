package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.product.param.SkuInfoParam;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;

import com.ddg.meituan.product.dao.SkuInfoDao;
import com.ddg.meituan.product.entity.SkuInfoEntity;
import com.ddg.meituan.product.service.SkuInfoService;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    private final SkuInfoDao skuInfoDao;

    public SkuInfoServiceImpl(SkuInfoDao skuInfoDao) {
        this.skuInfoDao = skuInfoDao;
    }

    @Override
    public PageUtils<SkuInfoEntity> queryPage(PageParam param) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

    @Override
    public PageUtils<SkuInfoEntity> querySkuInfoByParam(SkuInfoParam param) {
        Page<SkuInfoEntity> page = new Page<>(Long.parseLong(param.getPage()), Long.parseLong(param.getLimit()));

        IPage<SkuInfoEntity> iPage = skuInfoDao.querySkuInfoByParam(page, param);
        return  PageUtils.of(iPage);
    }

}