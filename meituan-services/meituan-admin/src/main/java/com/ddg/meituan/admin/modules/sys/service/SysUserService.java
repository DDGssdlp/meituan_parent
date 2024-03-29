

package com.ddg.meituan.admin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.admin.modules.sys.domain.SysUserEntity;
import com.ddg.meituan.admin.modules.sys.domain.param.SysUserPageParam;
import com.ddg.meituan.base.utils.PageUtils;


import java.util.List;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserService extends IService<SysUserEntity> {

	PageUtils<SysUserEntity> queryPage(SysUserPageParam param);

	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	/**
	 * 保存用户
	 */
	void saveUser(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);
	
	/**
	 * 删除用户
	 */
	void deleteBatch(Long[] userIds);

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	boolean updatePassword(Long userId, String password, String newPassword);

    List<String> queryRole(Long userId);
}
