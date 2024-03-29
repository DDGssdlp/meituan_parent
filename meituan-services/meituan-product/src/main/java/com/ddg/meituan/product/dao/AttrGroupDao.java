package com.ddg.meituan.product.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.product.domain.AttrGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 属性分组
 * 
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    IPage<AttrGroupEntity> selectAttrById(Page<?> page, @Param("param") PageParam pageParam);
}
