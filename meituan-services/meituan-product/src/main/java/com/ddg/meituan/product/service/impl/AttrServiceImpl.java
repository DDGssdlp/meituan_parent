package com.ddg.meituan.product.service.impl;

import com.ddg.meituan.product.vo.AttrRespVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.Query;

import com.ddg.meituan.product.dao.AttrDao;
import com.ddg.meituan.product.entity.AttrEntity;
import com.ddg.meituan.product.service.AttrService;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Override
    public PageUtils<AttrEntity> queryPage(PageParam param) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(param),
                wrapper
        );

        return PageUtils.of(page);
    }

    @Override
    public PageUtils<AttrRespVo> queryBaseAttrPage(PageParam pageParam, String type) {
        return null;
    }

}