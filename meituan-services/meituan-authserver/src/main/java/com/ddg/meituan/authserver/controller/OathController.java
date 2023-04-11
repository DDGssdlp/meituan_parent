package com.ddg.meituan.authserver.controller;


import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.domain.Oauth2TokenDto;
import com.ddg.meituan.base.exception.MeituanLoginException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("Oauth2获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", value = "登录用户名"),
            @ApiImplicitParam(name = "password", value = "登录密码")
    })
    @PostMapping("/token")
    public CommonResult<Oauth2TokenDto> postAccessToken(Principal principal,
    @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {


        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        if (oAuth2AccessToken == null){
            throw  new MeituanLoginException("获取token失败");
        }

        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        return CommonResult.success(oauth2TokenDto);
    }


}
