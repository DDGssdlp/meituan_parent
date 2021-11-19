package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.common.utils.RandomUtil;
import com.ddg.meituan.product.constant.BrandConstant;
import com.ddg.meituan.product.constant.ProductConstant;
import com.ddg.meituan.product.dao.CategoryBrandRelationDao;
import com.ddg.meituan.product.entity.CategoryBrandRelationEntity;
import com.ddg.meituan.product.vo.BrandListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ddg.meituan.product.dao.BrandDao;
import com.ddg.meituan.product.entity.BrandEntity;
import com.ddg.meituan.product.service.BrandService;
import org.springframework.util.CollectionUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    private final BrandDao brandDao;
    private final CategoryBrandRelationDao categoryBrandRelationDao;

    @Autowired
    public BrandServiceImpl(BrandDao brandDao, CategoryBrandRelationDao categoryBrandRelationDao) {
        this.brandDao = brandDao;
        this.categoryBrandRelationDao = categoryBrandRelationDao;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void updateStatus(BrandEntity brandEntity) {
        brandDao.updateById(brandEntity);
    }

    @Override
    public List<BrandEntity> getByBrandIds(List<Long> ids) {
        return brandDao.selectBatchIds(ids);
    }

    @Override
    public PageUtils queryBrandListPage(Map<String, Object> params) {
        String catId = (String) params.get(ProductConstant.CART_ID);
        // 使用catID 将下面的所有品牌进行查询
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(BrandConstant.CATELOG_ID, catId);
        List<CategoryBrandRelationEntity> list = categoryBrandRelationDao.selectList(wrapper);
        // 没有直接返回
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        // 获取所有的brand id
        List<Long> brandIds = list.stream()
                .map(CategoryBrandRelationEntity::getBrandId).collect(Collectors.toList());
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>().in(BrandConstant.BRAND_ID, brandIds)
        );
        List<BrandListVo> brandListVos = page.getRecords().stream().map(brandEntity -> {
            BrandListVo brandListVo = new BrandListVo();
            brandListVo.setAddr(brandEntity.getAddress());
            brandListVo.setBrandId(brandEntity.getBrandId());
            brandListVo.setImg(brandEntity.getLogo());
            brandListVo.setName(brandEntity.getName());
            Date createTime = brandEntity.getCreateTime();
            boolean diffSevenDays = compareDate(createTime, new Date());
            brandListVo.setStatus(diffSevenDays ? BrandConstant.OLD_BRAND : BrandConstant.NEW_BRAND);
            brandListVo.setType("景店");
            brandListVo.setComment(Integer.valueOf(RandomUtil.getFourBitRandom()));
            brandListVo.setRate((int) (Math.random() * 5));
            return brandListVo;
        }).collect(Collectors.toList());

        Page<BrandListVo>  brandListVoPage  = new Page<>();
        brandListVoPage.setCurrent(page.getCurrent());
        brandListVoPage.setRecords(brandListVos);
        brandListVoPage.setTotal(page.getTotal());
        brandListVoPage.setPages(page.getPages());
        return new PageUtils(brandListVoPage);
    }

    private boolean compareDate(Date createTime, Date date) {
       // 比较两个日期是否是相差七天：
        long time = createTime.getTime();
        long time1 = date.getTime();
        return (time1 - time) > BrandConstant.SEVEN_DAY;
    }

}