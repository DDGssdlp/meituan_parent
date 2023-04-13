package com.ddg.meituan.authserver.config;

import com.ddg.meituan.authserver.service.impl.UserServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Map;
import java.util.Optional;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author 13060
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2023/4/13 13:28
 * @email: wangzhijie0908@gmail.com
 */
public class SmsCodeTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "sms_code";

    protected UserServiceImpl userDetailsService;

    private final OAuth2RequestFactory requestFactory;

    public SmsCodeTokenGranter(UserServiceImpl userDetailsService,
                               AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.userDetailsService = userDetailsService;
        this.requestFactory = requestFactory;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = tokenRequest.getRequestParameters();
        UserDetails user = this.getUser(parameters);
        Optional.ofNullable(user).orElseThrow(() -> new InvalidGrantException("无法获取用户信息"));
        OAuth2Request storedOAuth2Request = this.requestFactory.createOAuth2Request(client, tokenRequest);
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(user, null, user.getAuthorities());
        authentication.setDetails(user);
        return new OAuth2Authentication(storedOAuth2Request, authentication);
    }

    private UserDetails getUser(Map<String, String> params) {
        return userDetailsService.loadUserByUsernameAndSmsCode(params.get("username"), params.get("phoneCode"));
    }
}