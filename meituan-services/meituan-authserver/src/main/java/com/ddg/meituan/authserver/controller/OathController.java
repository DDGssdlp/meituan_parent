package com.ddg.meituan.authserver.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ddg.meituan.common.constant.AuthConstant;
import com.ddg.meituan.common.domain.Oauth2TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Map;

/**
 * Description: 获取token接口 实现自定义返回格式
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/3/4 16:34
 * @email: wangzhijie0908@gmail.com
 */
@RestController
@RequestMapping("/oauth")
public class OathController {

    private final TokenEndpoint tokenEndpoint;

    @Autowired
    public OathController(TokenEndpoint tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }


    @PostMapping("/token")
    public R postAccessToken( Principal principal,  @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {


        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        return R.ok(oauth2TokenDto);
    }
}
