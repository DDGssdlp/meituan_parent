package com.ddg.meituan.coupon.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;

import com.ddg.meituan.coupon.dao.CouponSpuCategoryRelationDao;
import com.ddg.meituan.coupon.entity.CouponSpuCategoryRelationEntity;
import com.ddg.meituan.coupon.service.CouponSpuCategoryRelationService;


@Service("couponSpuCategoryRelationService")
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<CouponSpuCategoryRelationDao, CouponSpuCategoryRelationEntity> implements CouponSpuCategoryRelationService {

    @Override
    public PageUtils<CouponSpuCategoryRelationEntity> queryPage(PageParam param) {
        IPage<CouponSpuCategoryRelationEntity> page = this.page(
                new Query<CouponSpuCategoryRelationEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

}