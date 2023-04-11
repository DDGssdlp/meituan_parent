package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.entity.ProductInfoEntity;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/5 16:47
 * @email: wangzhijie0908@gmail.com
 */
public interface ProductInfoService extends IService<ProductInfoEntity> {

    PageUtils queryPage(PageParam param);

    void saveProduct(ProductInfoEntity productInfoEntity);

    void upStateById(Long id);
}
