package com.ddg.meituan.authserver.controller;



import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.domain.Oauth2TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: Oath2Controller</p>
 * Description：
 * date：2020/6/26 14:14
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class Oath2Controller {


	private final TokenEndpoint tokenEndpoint;

	@Autowired
	public Oath2Controller(TokenEndpoint tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
	}

	@GetMapping("qq/success")
	public CommonResult<Oauth2TokenDto> qqLogin(){

		// TODO 登录或者是注册：
		return CommonResult.success();
	}




}
