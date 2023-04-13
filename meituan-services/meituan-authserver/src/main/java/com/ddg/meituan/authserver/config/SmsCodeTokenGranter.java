package com.ddg.meituan.authserver.config;

import com.ddg.meituan.authserver.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;

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

    private static final String PHONE_NUM = "phone_num";

    private static final String PHONE_CODE = "phone_code";

    protected CustomUserDetailsService userDetailsService;

    private final OAuth2RequestFactory requestFactory;

    public SmsCodeTokenGranter(CustomUserDetailsService userDetailsService,
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
        String phone = params.get(PHONE_NUM);
        String phoneCode = params.get(PHONE_CODE);

        if(StringUtils.isEmpty(phoneCode) || StringUtils.isEmpty(phone)){
            throw new InvalidGrantException("手机号 或验证码不存在");
        }

        return userDetailsService.loadUserByUsernameAndSmsCode(phone, phoneCode);
    }
}