package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.api.Query;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.dao.AttrAttrgroupRelationDao;
import com.ddg.meituan.product.domain.AttrAttrgroupRelationEntity;
import com.ddg.meituan.product.domain.vo.AttrGroupRelationVo;
import com.ddg.meituan.product.service.AttrAttrgroupRelationService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils<AttrAttrgroupRelationEntity> queryPage(PageParam param) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

    @Override
    public boolean saveBatchByVo(List<AttrGroupRelationVo> vos) {

        if (!CollectionUtils.isEmpty(vos)){
            List<AttrAttrgroupRelationEntity> collect = vos.stream().map((item) -> {
                AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
                relationEntity.setAttrId(item.getAttrId());
                relationEntity.setAttrGroupId(item.getAttrGroupId());
                return relationEntity;
            }).collect(Collectors.toList());
            return this.saveBatch(collect);
        }
        return false;
    }

}