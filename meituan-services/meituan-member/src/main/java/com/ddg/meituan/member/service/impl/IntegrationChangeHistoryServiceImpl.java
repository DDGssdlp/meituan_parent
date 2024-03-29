package com.ddg.meituan.member.service.impl;

import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ddg.meituan.member.dao.IntegrationChangeHistoryDao;
import com.ddg.meituan.member.entity.IntegrationChangeHistoryEntity;
import com.ddg.meituan.member.service.IntegrationChangeHistoryService;


@Service("integrationChangeHistoryService")
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistoryEntity> implements IntegrationChangeHistoryService {

    @Override
    public PageUtils queryPage(PageParam param) {
        IPage<IntegrationChangeHistoryEntity> page = this.page(
                new Query<IntegrationChangeHistoryEntity>().getPage(param),
                new QueryWrapper<IntegrationChangeHistoryEntity>()
        );

        return new PageUtils(page);
    }

}