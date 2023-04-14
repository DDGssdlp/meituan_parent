package com.ddg.meituan.member.service.impl;

import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ddg.meituan.member.dao.MemberCollectSubjectDao;
import com.ddg.meituan.member.entity.MemberCollectSubjectEntity;
import com.ddg.meituan.member.service.MemberCollectSubjectService;


@Service("memberCollectSubjectService")
public class MemberCollectSubjectServiceImpl extends ServiceImpl<MemberCollectSubjectDao, MemberCollectSubjectEntity> implements MemberCollectSubjectService {

    @Override
    public PageUtils queryPage(PageParam param) {
        IPage<MemberCollectSubjectEntity> page = this.page(
                new Query<MemberCollectSubjectEntity>().getPage(param),
                new QueryWrapper<MemberCollectSubjectEntity>()
        );

        return new PageUtils(page);
    }

}