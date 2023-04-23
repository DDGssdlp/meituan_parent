package com.ddg.meituan.product.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.product.domain.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddg.meituan.product.domain.param.SpuInfoParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 * 
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {
    /**
     * 通过param 获取 spuinfo 信息分页返回
     * @param page
     * @param param
     * @return
     */
    IPage<SpuInfoEntity> selectSpuInfoByParam(Page<SpuInfoEntity> page, @Param("param") SpuInfoParam param);
}
