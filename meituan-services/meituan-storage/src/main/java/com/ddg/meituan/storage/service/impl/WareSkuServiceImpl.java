package com.ddg.meituan.storage.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;

import com.ddg.meituan.storage.dao.WareSkuDao;
import com.ddg.meituan.storage.entity.WareSkuEntity;
import com.ddg.meituan.storage.service.WareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageUtils<WareSkuEntity> queryPage(PageParam param) {
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

}