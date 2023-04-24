package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.product.domain.AttrAttrgroupRelationEntity;
import com.ddg.meituan.product.domain.vo.AttrGroupRelationVo;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils<AttrAttrgroupRelationEntity> queryPage(PageParam param);

    boolean saveBatchByVo(List<AttrGroupRelationVo> vos);
}

