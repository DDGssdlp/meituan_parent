package com.ddg.meituan.product.service.impl;

import com.ddg.meituan.product.dao.AttrAttrgroupRelationDao;
import com.ddg.meituan.product.dao.AttrGroupDao;
import com.ddg.meituan.product.entity.AttrAttrgroupRelationEntity;
import com.ddg.meituan.product.entity.AttrGroupEntity;
import com.ddg.meituan.product.entity.CategoryEntity;
import com.ddg.meituan.product.enums.ProductEnum;
import com.ddg.meituan.product.service.CategoryService;
import com.ddg.meituan.product.vo.AttrGroupRelationVo;
import com.ddg.meituan.product.vo.AttrRespVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.Query;

import com.ddg.meituan.product.dao.AttrDao;
import com.ddg.meituan.product.entity.AttrEntity;
import com.ddg.meituan.product.service.AttrService;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    public final AttrAttrgroupRelationDao relationDao;

    private final AttrGroupDao attrGroupDao;

    private final CategoryService categoryService;

    public AttrServiceImpl(AttrAttrgroupRelationDao relationDao, AttrGroupDao attrGroupDao, CategoryService categoryService) {
        this.relationDao = relationDao;
        this.attrGroupDao = attrGroupDao;
        this.categoryService = categoryService;
    }

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
    public PageUtils<AttrRespVo> queryAttrPageByType(PageParam pageParam, String type) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>()
                .eq("attr_type", ProductEnum.AttrType.BASE.getMsg().equalsIgnoreCase(type) ?
                        ProductEnum.AttrType.BASE.getCode() : ProductEnum.AttrType.SALE.getCode());

        //根据categoryId查询分类信息
        Long categoryId = pageParam.getId();
        if (categoryId != null && categoryId != 0L) {
            queryWrapper.eq("category_id", categoryId);
        }
        String key = pageParam.getKey();
        if (!StringUtils.isEmpty(key)) {
            //attr_id attr_name
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(pageParam),
                queryWrapper
        );
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> collect = records.stream().map(attrEntity -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            //设置分类和分组的名字
            if (ProductEnum.AttrType.BASE.getMsg().equalsIgnoreCase(type)) {
                AttrAttrgroupRelationEntity relationEntity =
                        relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if (relationEntity != null && relationEntity.getAttrGroupId() != null) {
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(relationEntity.getAttrGroupId());
                    if(attrGroupEntity != null){
                        attrRespVo.setAttrGroupName(attrGroupEntity.getAttrGroupName());
                    }
                }

            }
            CategoryEntity categoryEntity = categoryService.getById(attrEntity.getCategoryId());
            if (categoryEntity != null) {
                attrRespVo.setCategoryName(categoryEntity.getName());

            }
            return attrRespVo;
        }).collect(Collectors.toList());
        PageUtils<AttrRespVo> pageUtils = new PageUtils<>(collect, page.getTotal(), page.getSize(), page.getCurrent());

        return pageUtils;
    }

    @Override
    public AttrEntity getInfoById(Long attrId) {
        AttrEntity attrEntity = this.getById(attrId);
        if(attrEntity != null && attrEntity.getCategoryId() != null){
            attrEntity.setCategoryPath(categoryService.findCategoryPath(attrEntity.getCategoryId(), true));
        }
        return attrEntity;
    }

    @Override
    public PageUtils<AttrEntity> getNoRelationAttr(PageParam pageParam) {
        final Long attrGroupId = pageParam.getId();
        if(attrGroupId != null){
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
            //获取当前分类的id
            if(Objects.isNull(attrGroupEntity)){
                return PageUtils.of(null);
            }
            //1、当前分组只能关联自己所属的分类里面的所有属性

            Long categoryId = attrGroupEntity.getCategoryId();

            //2、当前分组只能关联别的分组没有引用的属性
            //2.1）、当前分类下的其它分组
            List<AttrGroupEntity> groupEntities = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>()
                    .eq("category_id", categoryId));

            //获取到所有的attrGroupId
            List<Long> collect = groupEntities.stream().map(AttrGroupEntity::getAttrGroupId).collect(Collectors.toList());


            //2.2）、这些分组关联的属性
            List<AttrAttrgroupRelationEntity> groupId = relationDao.selectList
                    (new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", collect));

            List<Long> attrIds = groupId.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());

            //2.3）、从当前分类的所有属性移除这些属性
            QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>()
                    .eq("category_id", categoryId).eq("attr_type",ProductEnum.AttrType.BASE.getCode());

            if (!CollectionUtils.isEmpty(attrIds)) {
                queryWrapper.notIn("attr_id", attrIds);
            }
            //判断是否有参数进行模糊查询
            String key = pageParam.getKey();
            if (!StringUtils.isEmpty(key)) {
                queryWrapper.and((w) -> {
                    w.eq("attr_id",key).or().like("attr_name",key);
                });
            }


            IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(pageParam), queryWrapper);
            return PageUtils.of(page);
        }

        return PageUtils.of(null);
    }

    @Override
    public List<AttrEntity> getRelationAttr(Long id) {
        List<AttrAttrgroupRelationEntity> entities = relationDao.selectList
                (new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", id));

        List<Long> attrIds = entities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        List<AttrEntity> attrEntityList = null;
        if(!CollectionUtils.isEmpty(attrIds)){
            attrEntityList = this.baseMapper.selectBatchIds(attrIds);
        }

        return attrEntityList;
    }

    @Override
    public void deleteRelation(List<AttrGroupRelationVo> vos) {
        if(!CollectionUtils.isEmpty(vos)){
            relationDao.deleteBatchRelation(vos);
        }
    }



}