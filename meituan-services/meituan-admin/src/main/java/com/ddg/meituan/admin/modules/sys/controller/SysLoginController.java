

package com.ddg.meituan.admin.modules.sys.controller;

import com.ddg.meituan.admin.modules.sys.entity.SysUserEntity;
import com.ddg.meituan.admin.modules.sys.service.SysCaptchaService;
import com.ddg.meituan.admin.modules.sys.service.SysUserService;
import com.ddg.meituan.base.api.CommonResult;

import com.ddg.meituan.base.domain.UserDto;
import com.ddg.meituan.base.exception.MeituanSysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

	private final SysCaptchaService sysCaptchaService;
	private final SysUserService sysUserService;


	public SysLoginController(SysCaptchaService sysCaptchaService, SysUserService sysUserService) {
		this.sysCaptchaService = sysCaptchaService;
		this.sysUserService = sysUserService;
	}

	/**
	 * 验证码
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid){
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		try {
			//获取图片验证码
			BufferedImage image = sysCaptchaService.getCaptcha(uuid);

			ServletOutputStream out = response.getOutputStream();
			ImageIO.write(image, "jpg", out);
			IOUtils.closeQuietly(out);
		}catch (IOException e){
			log.error("验证码生成失败");
			throw new MeituanSysException("验证码生成失败");
		}

	}

	/**
	 * 登录
	 */
	@GetMapping("/loadByUsername")
	public CommonResult<UserDto> loadByUsername(@RequestParam String username, @RequestParam  String code, @RequestParam String uuid){

		boolean captcha = sysCaptchaService.validate(uuid, code);
		if(!captcha){
			return CommonResult.failed("验证码失效!");
		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(username);
		List<String> roleList = null;
		if(Objects.nonNull(user)){
			roleList = sysUserService.queryRole(user.getUserId());
		}

		UserDto userDto = UserDto.builder().id(user.getUserId()).clientId(AuthConstant.ADMIN_CLIENT_ID)
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
