package com.ddg.meituan.product.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;

import com.ddg.meituan.product.dao.ProductAttrValueDao;
import com.ddg.meituan.product.entity.ProductAttrValueEntity;
import com.ddg.meituan.product.service.ProductAttrValueService;

import java.util.List;
import java.util.stream.Collectors;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    public PageUtils<ProductAttrValueEntity> queryPage(PageParam param) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

    @Override
    public List<ProductAttrValueEntity> getBaseAttrOfSpu(String spuId) {
        return this.list(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId));
    }

    @Override
    public void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities) {
        //1、删除spuId之前对应的所有属性
        this.remove(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id",spuId));

        //2、添加商品规格信息
        List<ProductAttrValueEntity> collect = entities.stream().peek(item -> {
            item.setSpuId(spuId);
        }).collect(Collectors.toList());

        //批量新增
        this.saveBatch(collect);
    }

}