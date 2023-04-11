package com.ddg.meituan.coupon.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;

import com.ddg.meituan.coupon.dao.CouponSpuRelationDao;
import com.ddg.meituan.coupon.entity.CouponSpuRelationEntity;
import com.ddg.meituan.coupon.service.CouponSpuRelationService;


@Service("couponSpuRelationService")
public class CouponSpuRelationServiceImpl extends ServiceImpl<CouponSpuRelationDao, CouponSpuRelationEntity> implements CouponSpuRelationService {

    @Override
    public PageUtils<CouponSpuRelationEntity> queryPage(PageParam param) {
        IPage<CouponSpuRelationEntity> page = this.page(
                new Query<CouponSpuRelationEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

}