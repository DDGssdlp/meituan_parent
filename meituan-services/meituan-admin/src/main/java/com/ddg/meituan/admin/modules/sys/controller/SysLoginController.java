package com.ddg.meituan.admin.modules.sys.controller;

import com.ddg.meituan.admin.modules.sys.domain.SysUserEntity;
import com.ddg.meituan.admin.modules.sys.service.SysUserService;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.constant.BaseConstant;
import com.ddg.meituan.base.domain.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 登录相关
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@Slf4j
@RequestMapping("/sys/login")
public class SysLoginController {


	private final SysUserService sysUserService;


	public SysLoginController(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}


	/**
	 * 登录
	 */
	@GetMapping("/loadByUsername")
	public CommonResult<UserDto> loadByUsername(@RequestParam String username){

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(username);
		List<String> roleList = null;
		if(Objects.nonNull(user)){
			roleList = sysUserService.queryRole(user.getUserId());
		}

		UserDto userDto = UserDto.builder().id(user.getUserId()).clientId(BaseConstant.ADMIN_CLIENT_ID)
				.password(user.getPassword()).status(user.getStatus()).username(user.getUsername()).roles(roleList).build();

		return CommonResult.success(userDto);

	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	@Deprecated
	public CommonResult<?> logout() {
		return CommonResult.success();
	}
	
}
