package com.ddg.meituan.admin.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddg.meituan.admin.modules.sys.domain.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
