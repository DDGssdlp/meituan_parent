package com.ddg.meituan.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.Query;
import com.ddg.meituan.common.utils.RandomUtil;
import com.ddg.meituan.product.constant.BrandConstant;
import com.ddg.meituan.product.constant.ProductConstant;
import com.ddg.meituan.product.dao.BrandDao;
import com.ddg.meituan.product.dao.CategoryBrandRelationDao;
import com.ddg.meituan.product.entity.BrandEntity;
import com.ddg.meituan.product.entity.CategoryBrandRelationEntity;
import com.ddg.meituan.product.service.BrandService;
import com.ddg.meituan.product.vo.BrandListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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
    public PageUtils<BrandEntity> queryPage(PageParam param) {
        String key = param.getKey();
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(param),
                new QueryWrapper<BrandEntity>()
                        .like(!StringUtils.isEmpty(key), ProductConstant.NAME, key)
                        .or().like(!StringUtils.isEmpty(key), "description", key)
        );

        return PageUtils.of(page);
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
    public PageUtils<BrandListVo> queryBrandListPage(PageParam param) {
        Long catId = param.getId();
        // 使用catID 将下面的所有品牌进行查询
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(BrandConstant.CATEGORY_ID, catId);
        List<CategoryBrandRelationEntity> list = categoryBrandRelationDao.selectList(wrapper);
        // 没有直接返回
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 获取所有的brand id
        List<Long> brandIds = list.stream()
                .map(CategoryBrandRelationEntity::getBrandId).distinct().collect(Collectors.toList());
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(param),
                new QueryWrapper<BrandEntity>().in(BrandConstant.BRAND_ID, brandIds)
                        .eq(BrandConstant.SHOW_STATUS, 1)
        );
        List<BrandListVo> brandListVos = page.getRecords().stream().map(brandEntity -> {
            BrandListVo brandListVo = new BrandListVo();
            brandListVo.setAddr(brandEntity.getAddress());
            brandListVo.setBrandId(brandEntity.getBrandId());
            brandListVo.setImg(brandEntity.getLogo());
            brandListVo.setName(brandEntity.getName());
            LocalDateTime createTime = brandEntity.getCreateTime();
            boolean diffSevenDays = compareDate(createTime, LocalDateTime.now());
            brandListVo.setStatus(diffSevenDays ? BrandConstant.OLD_BRAND : BrandConstant.NEW_BRAND);
            // todo: 商家的类型 和 评论 补充
            brandListVo.setType("景店");
            brandListVo.setComment(Integer.valueOf(RandomUtil.getFourBitRandom()));
            brandListVo.setRate((int) (Math.random() * 5) + 1);
            brandListVo.setDescription(brandEntity.getDescription());
            brandListVo.setPrice(brandEntity.getPrice());
            return brandListVo;
        }).collect(Collectors.toList());

        Page<BrandListVo> brandListVoPage = new Page<>();
        brandListVoPage.setCurrent(page.getCurrent());
        brandListVoPage.setRecords(brandListVos);
        brandListVoPage.setTotal(page.getTotal());
        brandListVoPage.setPages(page.getPages());
        return PageUtils.of(brandListVoPage);
    }

    private boolean compareDate(LocalDateTime createTime, LocalDateTime now) {
        // 比较两个日期是否是相差七天：
        return Duration.between(createTime, now).toDays() > 7;

    }

}