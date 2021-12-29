package com.ddg.meituan.product.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.Query;

import com.ddg.meituan.product.dao.SpuImagesDao;
import com.ddg.meituan.product.entity.SpuImagesEntity;
import com.ddg.meituan.product.service.SpuImagesService;

import java.util.List;
import java.util.stream.Collectors;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    public PageUtils<SpuImagesEntity> queryPage(PageParam param) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

    @Override
    public void saveImages(Long id, List<String> images) {
        List<SpuImagesEntity> collect = images.stream().map(img -> {
            SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
            spuImagesEntity.setSpuId(id);
            spuImagesEntity.setImgUrl(img);
            return spuImagesEntity;
        }).collect(Collectors.toList());

        this.saveBatch(collect);
    }

}