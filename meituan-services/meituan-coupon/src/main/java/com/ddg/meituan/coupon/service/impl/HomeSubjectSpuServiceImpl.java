package com.ddg.meituan.coupon.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;

import com.ddg.meituan.coupon.dao.HomeSubjectSpuDao;
import com.ddg.meituan.coupon.entity.HomeSubjectSpuEntity;
import com.ddg.meituan.coupon.service.HomeSubjectSpuService;


@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuDao, HomeSubjectSpuEntity> implements HomeSubjectSpuService {

    @Override
    public PageUtils<HomeSubjectSpuEntity> queryPage(PageParam param) {
        IPage<HomeSubjectSpuEntity> page = this.page(
                new Query<HomeSubjectSpuEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

}