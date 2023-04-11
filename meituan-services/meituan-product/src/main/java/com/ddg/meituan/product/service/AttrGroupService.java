package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.product.entity.AttrGroupEntity;
import com.ddg.meituan.product.vo.AttrGroupWithAttrsVo;

import java.util.List;

/**
 * 属性分组
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils<AttrGroupEntity> queryPage(PageParam param);

    AttrGroupEntity getInfoById(Long attrGroupId);

    /**
     * 获取 分类下的所有的属性组 和 属性
     * @param categoryId
     * @returnC
     */
    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCategoryId(Long categoryId);

    void removeGroupAndRelationByIds(List<Long> asList);
}

