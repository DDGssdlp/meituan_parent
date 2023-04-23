package com.ddg.meituan.product.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.product.domain.SkuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddg.meituan.product.domain.param.SkuInfoParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * sku信息
 * 
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
@Mapper
public interface SkuInfoDao extends BaseMapper<SkuInfoEntity> {

    IPage<SkuInfoEntity> querySkuInfoByParam(Page<SkuInfoEntity> page, @Param("param") SkuInfoParam param);
}
