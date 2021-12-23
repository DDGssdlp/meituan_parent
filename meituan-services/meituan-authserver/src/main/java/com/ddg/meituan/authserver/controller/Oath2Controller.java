package com.ddg.meituan.authserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ddg.meituan.authserver.component.AuthEndpoint;
import com.ddg.meituan.authserver.config.ThirdPartyProperties;

import com.ddg.meituan.authserver.feign.MemberFeignService;
import com.ddg.meituan.authserver.vo.MemberRegisterVo;
import com.ddg.meituan.authserver.vo.MemberVo;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.constant.MemberConstant;
import com.ddg.meituan.common.domain.Oauth2TokenDto;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Title: Oath2Controller</p>
 * Description：
 * date：2020/6/26 14:14
 * @author DELL
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class Oath2Controller {


	private final ThirdPartyProperties thirdPartyProperties;

	private final AuthEndpoint authEndpoint;

	@Autowired
	public Oath2Controller(ThirdPartyProperties thirdPartyProperties, AuthEndpoint authEndpoint) {
		this.thirdPartyProperties = thirdPartyProperties;
		this.authEndpoint = authEndpoint;
	}

	@PostMapping("/{type}/Callback")
	public CommonResult<?> thirdPartyLoginCallback(@RequestParam("code") String code, @PathVariable String type,
												   HttpServletRequest servletRequest){
		ThirdPartyProperties.ThirdPartyEntity thirdPartyEntity = thirdPartyProperties.getThirdparty().get(type);
		try {
			OkHttpClient okHttpClient = new OkHttpClient();
			//请求参数
			JSONObject json = new JSONObject();
			json.put("client_secret", thirdPartyEntity.getClientSecret());
			RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(json));
			Request request = new Request.Builder().post(requestBody).url(buildGiteeUrl(type, code, thirdPartyEntity)).build();
			Call call = okHttpClient.newCall(request);
			Response response = call.execute();
			if(!Objects.isNull(response.body())){
				ThirdPartyProperties.JsonRootBean body = JSON.parseObject(response.body().string(),
						ThirdPartyProperties.JsonRootBean.class);
				// 使用 access_token 进行注册：
				request = new Request.Builder()
						.url(thirdPartyEntity.getUserUri() + body.getAccessToken())
						.get()
						.build();
				call = okHttpClient.newCall(request);
				response = call.execute();
				if(!Objects.isNull(response.body())){
					ThirdPartyProperties.UserInfo userInfo = JSON.parseObject(response.body().string(),
							ThirdPartyProperties.UserInfo.class);
					// TODO：社交登录获取用户的手机号
					OAuth2AccessToken token = authEndpoint.getToken(servletRequest, userInfo.getName(),
							MemberConstant.PHONE_CODE_MOCK, "portal-app", "123456");
					return CommonResult.success(token.getValue());

				}
			}

		} catch (IOException e) {
			log.error("第三方登录失败");
		}
		return CommonResult.failed();
	}

	private String buildGiteeUrl(String type, String code, ThirdPartyProperties.ThirdPartyEntity thirdPartyEntity){

		return "https://gitee.com/oauth/token?grant_type=authorization_code&code=" + code + "&client_id=" +
				thirdPartyEntity.getClientId() +"&redirect_uri=" + thirdPartyEntity.getCallbackUri();
	}






}
