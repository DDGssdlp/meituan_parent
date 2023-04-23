package com.ddg.meituan.admin.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.constant.Constant;
import com.ddg.meituan.admin.modules.sys.dao.SysUserDao;
import com.ddg.meituan.admin.modules.sys.domain.SysUserEntity;
import com.ddg.meituan.admin.modules.sys.domain.param.SysUserPageParam;
import com.ddg.meituan.admin.modules.sys.service.SysRoleService;
import com.ddg.meituan.admin.modules.sys.service.SysUserRoleService;
import com.ddg.meituan.admin.modules.sys.service.SysUserService;
import com.ddg.meituan.base.api.Query;
import com.ddg.meituan.base.exception.MeituanSysException;
import com.ddg.meituan.base.utils.PageUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

	private final PasswordEncoder passwordEncoder;

	private final SysUserRoleService sysUserRoleService;

	@Resource
	private SysRoleService sysRoleService;


	public SysUserServiceImpl(PasswordEncoder passwordEncoder, SysUserRoleService sysUserRoleService) {
		this.passwordEncoder = passwordEncoder;
		this.sysUserRoleService = sysUserRoleService;
	}


	@Override
	public PageUtils<SysUserEntity> queryPage(SysUserPageParam param) {


		Long createUserId = param.getCreateUserId();
		String username = param.getUsername();

		IPage<SysUserEntity> page = this.page(
				new Query<SysUserEntity>().getPage(param),
				new QueryWrapper<SysUserEntity>()
						.like(StringUtils.isNotBlank(username),"username", username)
						.eq(createUserId != null,"create_user_id", createUserId)
		);

		return PageUtils.of(page);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(SysUserEntity user) {

		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setSalt(salt);
		this.save(user);

		//检查角色是否越权
		checkRole(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		this.updateById(user);

		//检查角色是否越权
		checkRole(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

	}

	@Override
	public void deleteBatch(Long[] userId) {
		this.removeByIds(Arrays.asList(userId));
	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = this.getById(userId);
		if(!passwordEncoder.matches(password, userEntity.getPassword())){
			throw new MeituanSysException("密码不正确不能修改密码");
		}
		String newPasswordEncode = passwordEncoder.encode(newPassword);
		userEntity.setPassword(newPasswordEncode);
		return this.update(userEntity,
				new QueryWrapper<SysUserEntity>().eq("user_id", userId));
	}

	@Override
	public List<String> queryRole(Long userId) {
		return baseMapper.queryRole(userId);
	}

	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user){
		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
			return;
		}
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return;
		}

		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new MeituanSysException("新增用户所选角色，不是本人创建");
		}
	}
}