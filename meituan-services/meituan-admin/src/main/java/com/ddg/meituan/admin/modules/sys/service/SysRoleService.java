package com.ddg.meituan.admin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.admin.modules.sys.entity.SysRoleEntity;
import com.ddg.meituan.admin.modules.sys.entity.param.SysRolePageParam;
import com.ddg.meituan.base.utils.PageUtils;

import java.util.List;


/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysRoleService extends IService<SysRoleEntity> {

	PageUtils queryPage(SysRolePageParam params);

	void saveRole(SysRoleEntity role);

	void update(SysRoleEntity role);

	void deleteBatch(Long[] roleIds);

	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
