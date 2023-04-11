package com.ddg.meituan.coupon.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;

import com.ddg.meituan.coupon.dao.SeckillSkuNoticeDao;
import com.ddg.meituan.coupon.entity.SeckillSkuNoticeEntity;
import com.ddg.meituan.coupon.service.SeckillSkuNoticeService;


@Service("seckillSkuNoticeService")
public class SeckillSkuNoticeServiceImpl extends ServiceImpl<SeckillSkuNoticeDao, SeckillSkuNoticeEntity> implements SeckillSkuNoticeService {

    @Override
    public PageUtils<SeckillSkuNoticeEntity> queryPage(PageParam param) {
        IPage<SeckillSkuNoticeEntity> page = this.page(
                new Query<SeckillSkuNoticeEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

}