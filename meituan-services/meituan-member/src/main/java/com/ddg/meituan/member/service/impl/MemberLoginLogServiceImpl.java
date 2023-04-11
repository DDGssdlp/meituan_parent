package com.ddg.meituan.member.service.impl;

import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ddg.meituan.member.dao.MemberLoginLogDao;
import com.ddg.meituan.member.entity.MemberLoginLogEntity;
import com.ddg.meituan.member.service.MemberLoginLogService;


@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogDao, MemberLoginLogEntity> implements MemberLoginLogService {

    @Override
    public PageUtils queryPage(PageParam param) {
        IPage<MemberLoginLogEntity> page = this.page(
                new Query<MemberLoginLogEntity>().getPage(param),
                new QueryWrapper<MemberLoginLogEntity>()
        );

        return new PageUtils(page);
    }

}