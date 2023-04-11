package com.ddg.meituan.order.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;

import com.ddg.meituan.order.dao.RefundInfoDao;
import com.ddg.meituan.order.entity.RefundInfoEntity;
import com.ddg.meituan.order.service.RefundInfoService;


@Service("refundInfoService")
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoDao, RefundInfoEntity> implements RefundInfoService {

    @Override
    public PageUtils<RefundInfoEntity> queryPage(PageParam param) {
        IPage<RefundInfoEntity> page = this.page(
                new Query<RefundInfoEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

}