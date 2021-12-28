package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.product.entity.AttrGroupEntity;
import com.ddg.meituan.product.entity.param.SpuInfoParam;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.common.utils.PageUtils;

import com.ddg.meituan.product.dao.SpuInfoDao;
import com.ddg.meituan.product.entity.SpuInfoEntity;
import com.ddg.meituan.product.service.SpuInfoService;

import java.util.List;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {


    private final SpuInfoDao spuInfoDao;

    public SpuInfoServiceImpl(SpuInfoDao spuInfoDao) {
        this.spuInfoDao = spuInfoDao;
    }

    @Override
    public PageUtils<SpuInfoEntity> queryPage(SpuInfoParam param) {
        Page<SpuInfoEntity> page = new Page<>(Long.parseLong(param.getPage()), Long.parseLong(param.getLimit()));
        IPage<SpuInfoEntity> iPage = spuInfoDao.selectSpuInfoByParam(page, param);
        return PageUtils.of(iPage);
    }

}