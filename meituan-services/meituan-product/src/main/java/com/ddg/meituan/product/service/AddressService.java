package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.product.entity.AddressEntity;

/**
 * 地址表
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-31 12:09:54
 */
public interface AddressService extends IService<AddressEntity> {

    PageUtils<AddressEntity> queryPage(PageParam param);
}

