package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.product.entity.AttrEntity;
import com.ddg.meituan.product.vo.AttrGroupRelationVo;
import com.ddg.meituan.product.vo.AttrRespVo;

import java.util.List;

/**
 * 商品属性
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils<AttrEntity> queryPage(PageParam param);

    PageUtils<AttrRespVo> queryAttrPageByType(PageParam pageParam, String type);

    AttrEntity getInfoById(Long attrId);

    PageUtils<AttrEntity> getNoRelationAttr(PageParam pageParam);

    /**
     * 根据分组id找到关联的所有属性
     * @param id
     * @return
     */
    List<AttrEntity> getRelationAttr(Long id);

    void deleteRelation(List<AttrGroupRelationVo> vos);
}

