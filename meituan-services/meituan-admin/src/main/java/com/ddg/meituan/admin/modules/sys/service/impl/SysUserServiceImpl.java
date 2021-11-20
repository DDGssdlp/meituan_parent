

package com.ddg.meituan.admin.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.modules.sys.dao.SysUserDao;
import com.ddg.meituan.admin.modules.sys.entity.SysUserEntity;
import com.ddg.meituan.admin.modules.sys.service.SysUserService;
import com.ddg.meituan.common.utils.PageParam;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {


	@Override
	public Page queryPage(PageParam param) {
		return null;
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return null;
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return null;
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return null;
	}

	@Override
	public void saveUser(SysUserEntity user) {

	}

	@Override
	public void update(SysUserEntity user) {

	}

	@Override
	public void deleteBatch(Long[] userIds) {

	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		return false;
	}
}