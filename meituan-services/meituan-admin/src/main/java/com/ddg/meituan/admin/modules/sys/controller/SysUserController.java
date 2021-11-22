package com.ddg.meituan.admin.modules.sys.controller;

import com.ddg.meituan.admin.modules.sys.entity.SysUserEntity;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.constant.AuthConstant;
import com.ddg.meituan.common.domain.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {


	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public CommonResult<SysUserEntity> info(@RequestHeader(AuthConstant.USER_TOKEN_HEADER) String userDto){
		SysUserEntity sysUserEntity = new SysUserEntity();
		sysUserEntity.setUserId(1L);
		sysUserEntity.setUsername("zhangsan");

		return CommonResult.success(sysUserEntity);
	}
	



}
