package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.api.Query;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.dao.AddressDao;
import com.ddg.meituan.product.domain.AddressEntity;
import com.ddg.meituan.product.service.AddressService;
import org.springframework.stereotype.Service;


@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressDao, AddressEntity> implements AddressService {

    @Override
    public PageUtils<AddressEntity> queryPage(PageParam param) {
        IPage<AddressEntity> page = this.page(
                new Query<AddressEntity>().getPage(param),
                new QueryWrapper<>()
        );

        return PageUtils.of(page);
    }

}