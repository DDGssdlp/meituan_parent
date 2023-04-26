package com.ddg.meituan.admin.modules.sys.controller;


import com.ddg.meituan.admin.common.annotation.SysLog;
import com.ddg.meituan.admin.constant.Constant;
import com.ddg.meituan.admin.modules.sys.domain.SysRoleEntity;
import com.ddg.meituan.admin.modules.sys.domain.param.SysRolePageParam;
import com.ddg.meituan.admin.modules.sys.service.SysRoleMenuService;
import com.ddg.meituan.admin.modules.sys.service.SysRoleService;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.constant.BaseConstant;
import com.ddg.meituan.base.domain.UserDto;
import com.ddg.meituan.base.utils.PageUtils;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 角色管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

	private final SysRoleService sysRoleService;
	private final SysRoleMenuService sysRoleMenuService;

	@Autowired
	public SysRoleController(SysRoleService sysRoleService, SysRoleMenuService sysRoleMenuService) {
		this.sysRoleService = sysRoleService;
		this.sysRoleMenuService = sysRoleMenuService;
	}

	/**
	 * 角色列表
	 */
	@GetMapping("/list")
	public CommonResult<PageUtils<SysRoleEntity>> list(SysRolePageParam params,
										 @RequestHeader(BaseConstant.USER_TOKEN_HEADER) UserDto userDto){

		if(userDto.getId() != Constant.SUPER_ADMIN){
			params.setCreateUserId(userDto.getId());
		}

		PageUtils<SysRoleEntity> page = sysRoleService.queryPage(params);

		return CommonResult.success(page);
	}
	
	/**
	 * 角色列表
	 */
	@GetMapping("/select")
	public CommonResult<List<SysRoleEntity>> select( @RequestHeader(BaseConstant.USER_TOKEN_HEADER)UserDto userDto){
		HashMap<String, Object> map = Maps.newHashMap();

		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if(userDto.getId() != Constant.SUPER_ADMIN){
			map.put("create_user_id", userDto.getId());
		}
		List<SysRoleEntity> list = sysRoleService.listByMap(map);
		
		return CommonResult.success(list);
	}
	
	/**
	 * 角色信息
	 */
	@GetMapping("/info/{roleId}")
	public CommonResult<SysRoleEntity> info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.getById(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return CommonResult.success(role);
	}
	
	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@PostMapping("/save")
	public CommonResult<Object> save(@RequestBody SysRoleEntity role,
									 @RequestHeader(BaseConstant.USER_TOKEN_HEADER)UserDto userDto,
									 HttpServletRequest request){

		role.setCreateUserId(userDto.getId());
		sysRoleService.saveRole(role);
		
		return CommonResult.success();
	}
	
	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@PostMapping("/update")
	public CommonResult<Object> update(@RequestBody SysRoleEntity role){
		
		role.setCreateUserId(1L);
		sysRoleService.update(role);
		
		return CommonResult.success();
	}
	
	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@PostMapping("/delete")
	public CommonResult<Object> delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return CommonResult.success();
	}
}
