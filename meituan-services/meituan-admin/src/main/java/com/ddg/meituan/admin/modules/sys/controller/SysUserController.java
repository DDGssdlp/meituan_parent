package com.ddg.meituan.admin.modules.sys.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ddg.meituan.admin.common.utils.Constant;
import com.ddg.meituan.admin.modules.sys.entity.SysUserEntity;
import com.ddg.meituan.admin.modules.sys.service.SysUserService;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.constant.AuthConstant;
import com.ddg.meituan.common.domain.UserDto;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

	private final SysUserService sysUserService;

	@Autowired
	public SysUserController(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	public CommonResult<PageUtils> list(PageParam params,
										@RequestHeader(AuthConstant.USER_TOKEN_HEADER) UserDto userDto){
		//只有超级管理员，才能查看所有管理员列表
		if(userDto.getId() != Constant.SUPER_ADMIN){

		}
		PageUtils page = sysUserService.queryPage(params);

		return CommonResult.success(page);
	}


	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public CommonResult<SysUserEntity> info(@RequestHeader(value = AuthConstant.USER_TOKEN_HEADER) UserDto userDto){

		SysUserEntity sysUserEntity = sysUserService.getById(userDto.getId());
		return CommonResult.success(sysUserEntity);
	}
	



}
