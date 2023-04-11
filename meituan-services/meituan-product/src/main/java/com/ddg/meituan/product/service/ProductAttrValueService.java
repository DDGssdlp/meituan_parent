package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.product.entity.ProductAttrValueEntity;

import java.util.List;

/**
 * spu属性值
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils<ProductAttrValueEntity> queryPage(PageParam param);

    List<ProductAttrValueEntity> getBaseAttrOfSpu(String spuId);

    void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);
}

