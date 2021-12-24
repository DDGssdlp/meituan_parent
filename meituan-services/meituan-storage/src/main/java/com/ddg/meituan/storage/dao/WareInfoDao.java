package com.ddg.meituan.storage.dao;

import com.ddg.meituan.storage.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 * 
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:33:05
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {
	
}
