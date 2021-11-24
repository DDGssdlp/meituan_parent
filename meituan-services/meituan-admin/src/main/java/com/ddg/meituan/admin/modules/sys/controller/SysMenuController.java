package com.ddg.meituan.admin.modules.sys.controller;


import com.ddg.meituan.admin.common.annotation.SysLog;
import com.ddg.meituan.admin.common.utils.Constant;
import com.ddg.meituan.admin.modules.sys.entity.SysMenuEntity;
import com.ddg.meituan.admin.modules.sys.service.SysMenuService;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.constant.AuthConstant;
import com.ddg.meituan.common.domain.UserDto;
import com.ddg.meituan.common.exception.MeituanSysException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 系统菜单
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController{

	private final SysMenuService sysMenuService;

	@Autowired
	public SysMenuController(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}


	/**
	 * 导航菜单
	 */
	@GetMapping("/nav")
	public CommonResult<List<SysMenuEntity>> nav(@RequestHeader(AuthConstant.USER_TOKEN_HEADER) UserDto user){
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(user.getId());

		return CommonResult.success(menuList);
	}
	
	/**
	 * 所有菜单列表
	 */
	@GetMapping("/list")
	public List<SysMenuEntity> list(){
		List<SysMenuEntity> menuList = sysMenuService.list();
		HashMap<Long, SysMenuEntity> menuMap = new HashMap<>(12);
		for (SysMenuEntity s : menuList) {
			menuMap.put(s.getMenuId(), s);
		}
		for (SysMenuEntity s : menuList) {
			SysMenuEntity parent = menuMap.get(s.getParentId());
			if (Objects.nonNull(parent)) {
				s.setParentName(parent.getName());
			}

		}


		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@GetMapping("/select")
	public CommonResult<List<SysMenuEntity>> select(){
		//查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return CommonResult.success(menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@GetMapping("/info/{menuId}")
	public CommonResult<SysMenuEntity> info(@PathVariable("menuId") Long menuId){
		SysMenuEntity menu = sysMenuService.getById(menuId);
		return CommonResult.success(menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@PostMapping("/save")

	public CommonResult<Object> save(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
		
		sysMenuService.save(menu);
		
		return CommonResult.success();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@PostMapping("/update")
	public CommonResult<Object> update(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
				
		sysMenuService.updateById(menu);
		
		return CommonResult.success();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@PostMapping("/delete/{menuId}")
	public CommonResult<Object> delete(@PathVariable("menuId") long menuId){
		if(menuId <= 31){
			return CommonResult.failed("系统菜单，不能删除");
		}

		//判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return CommonResult.failed("请先删除子菜单或按钮");
		}

		sysMenuService.delete(menuId);

		return CommonResult.success();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new MeituanSysException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new MeituanSysException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == Constant.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new MeituanSysException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = Constant.MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenuEntity parentMenu = sysMenuService.getById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
				menu.getType() == Constant.MenuType.MENU.getValue()){
			if(parentType != Constant.MenuType.CATALOG.getValue()){
				throw new MeituanSysException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
			if(parentType != Constant.MenuType.MENU.getValue()){
				throw new MeituanSysException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
