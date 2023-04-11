package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.product.entity.AttrAttrgroupRelationEntity;
import com.ddg.meituan.product.entity.AttrEntity;
import com.ddg.meituan.product.service.AttrAttrgroupRelationService;
import com.ddg.meituan.product.service.AttrService;
import com.ddg.meituan.product.service.CategoryService;
import com.ddg.meituan.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.product.dao.AttrGroupDao;
import com.ddg.meituan.product.entity.AttrGroupEntity;
import com.ddg.meituan.product.service.AttrGroupService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    private final AttrGroupDao attrGroupDao;

    private final CategoryService categoryService;

    private final AttrService attrService;

    private final AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    public AttrGroupServiceImpl(AttrGroupDao attrGroupDao, CategoryService categoryService, AttrService attrService, AttrAttrgroupRelationService attrAttrgroupRelationService) {
        this.attrGroupDao = attrGroupDao;
        this.categoryService = categoryService;
        this.attrService = attrService;
        this.attrAttrgroupRelationService = attrAttrgroupRelationService;
    }

    @Override
    public PageUtils<AttrGroupEntity> queryPage(PageParam param) {
        Page<AttrGroupEntity> page = new Page<>(Long.parseLong(param.getPage()), Long.parseLong(param.getLimit()));
        IPage<AttrGroupEntity> iPage = attrGroupDao.selectAttrById(page, param);
        return PageUtils.of(iPage);
    }

    @Override
    public AttrGroupEntity getInfoById(Long attrGroupId) {
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
        if(Objects.isNull(attrGroupEntity)){
            return null;
        }
        Long categoryId = attrGroupEntity.getCategoryId();
        if(categoryId != null){
            attrGroupEntity.setCategoryPath(categoryService.findCategoryPath(categoryId, true));
        }
        return attrGroupEntity;
    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCategoryId(Long categoryId) {
        //1、查询分组信息
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("category_id",
                categoryId));

        //2、查询所有属性
        List<AttrGroupWithAttrsVo> collect = attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group,attrGroupWithAttrsVo);

            List<AttrEntity> attrs = attrService.getRelationAttr(attrGroupWithAttrsVo.getAttrGroupId());
            attrGroupWithAttrsVo.setAttrs(attrs);

            return attrGroupWithAttrsVo;
        }).filter(attrGroupWithAttrsVo -> !CollectionUtils.isEmpty(attrGroupWithAttrsVo.getAttrs())).collect(Collectors.toList());

        return collect;
    }

    /**
     * 移除分组 同时将所关联的 属性进行释放
     * @param asList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeGroupAndRelationByIds(List<Long> asList) {
        if (!CollectionUtils.isEmpty(asList)) {
            this.removeByIds(asList);
            QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
            wrapper.in(!CollectionUtils.isEmpty(asList), "attr_group_id", asList);
            attrAttrgroupRelationService.remove(wrapper);
        }

    }

}