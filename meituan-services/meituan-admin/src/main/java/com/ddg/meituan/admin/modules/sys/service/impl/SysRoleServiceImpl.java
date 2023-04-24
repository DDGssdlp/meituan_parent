package com.ddg.meituan.admin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.constant.Constant;
import com.ddg.meituan.admin.modules.sys.dao.SysRoleDao;
import com.ddg.meituan.admin.modules.sys.domain.SysRoleEntity;
import com.ddg.meituan.admin.modules.sys.domain.param.SysRolePageParam;
import com.ddg.meituan.admin.modules.sys.service.SysRoleMenuService;
import com.ddg.meituan.admin.modules.sys.service.SysRoleService;
import com.ddg.meituan.admin.modules.sys.service.SysUserRoleService;
import com.ddg.meituan.admin.modules.sys.service.SysUserService;
import com.ddg.meituan.base.api.Query;
import com.ddg.meituan.base.exception.MeituanSysException;
import com.ddg.meituan.base.utils.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

	private final SysRoleMenuService sysRoleMenuService;

    private final SysUserRoleService sysUserRoleService;

    @Resource
	private SysUserService sysUserService;

    @Autowired
	public SysRoleServiceImpl(SysRoleMenuService sysRoleMenuService, SysUserRoleService sysUserRoleService) {
		this.sysRoleMenuService = sysRoleMenuService;
		this.sysUserRoleService = sysUserRoleService;
	}

	@Override
	public PageUtils<SysRoleEntity> queryPage(SysRolePageParam params) {
		String roleName = params.getRoleName();
		Long createUserId = params.getCreateUserId();

		IPage<SysRoleEntity> page = this.page(
			new Query<SysRoleEntity>().getPage(params),
			new QueryWrapper<SysRoleEntity>()
				.like(StringUtils.isNotBlank(roleName),"role_name", roleName)
				.eq(createUserId != null,"create_user_id", createUserId)
		);

		return PageUtils.of(page);
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleEntity role) {
        this.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }


    @Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return baseMapper.queryRoleIdList(createUserId);
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRoleEntity role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户所拥有的菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new MeituanSysException("新增角色的权限，已超出你的权限范围");
		}
	}
}
