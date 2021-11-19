

package com.ddg.meituan.admin.modules.sys.controller;

import cn.hutool.core.collection.CollUtil;
import com.ddg.meituan.admin.modules.entity.param.SysLoginForm;
import com.ddg.meituan.admin.modules.sys.entity.SysUserEntity;
import com.ddg.meituan.admin.modules.sys.service.SysCaptchaService;
import com.ddg.meituan.common.api.CommonResult;

import com.ddg.meituan.common.domain.UserDto;
import com.ddg.meituan.common.exception.MeituanSysException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/login")
public class SysLoginController {

	@Autowired
	private SysCaptchaService sysCaptchaService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 验证码
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@GetMapping("/loadByUsername")
	public UserDto loadByUsername(String username){


		//用户信息
		UserDto userDto = new UserDto(1L, "zhangsan", passwordEncoder.encode("123456"), 1, "admin-app", CollUtil.toList(
				"ADMIN"));


		return userDto;


	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	public CommonResult logout() {
		return null;
	}
	
}
