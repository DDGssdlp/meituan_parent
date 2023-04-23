package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.api.Query;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.dao.SpuInfoDescDao;
import com.ddg.meituan.product.domain.SpuInfoDescEntity;
import com.ddg.meituan.product.service.SpuInfoDescService;
import org.springframework.stereotype.Service;


@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDescEntity> implements SpuInfoDescService {

    @Override
    public PageUtils<SpuInfoDescEntity> queryPage(PageParam param) {
        IPage<SpuInfoDescEntity> page = this.page(
                new Query<SpuInfoDescEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

    @Override
    public void saveSpuInfoDesc(SpuInfoDescEntity spuInfoDescEntity) {
        this.baseMapper.insert(spuInfoDescEntity);
    }

}