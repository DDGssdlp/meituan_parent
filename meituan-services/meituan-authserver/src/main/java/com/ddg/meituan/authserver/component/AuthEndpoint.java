package com.ddg.meituan.authserver.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/22 22:29
 * @email: wangzhijie0908@gmail.com
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AuthEndpoint {

    private final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private final ClientDetailsService clientDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final AuthorizationServerEndpointsConfiguration conf;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    /**
     * 自定义获取令牌端点
     * 直接通过用户名和密码进行令牌申请，客户端信息直接通过后端配置
     *
     * @return
     */

    public OAuth2AccessToken getToken(HttpServletRequest request, String username,
                                                     String password, String clientId, String clientSecret) {
        // 1. 对客户端进行认证，ps: 可以不需要直接设置认证成功
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(clientId, clientSecret);
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        // 进行认证并返回
        Authentication authentication = authenticate(authenticationToken);
        // 认证失败
        if (authentication == null) {
            throw new InsufficientAuthenticationException(
                    "There is no client authentication. Try adding an appropriate authentication filter.");
        }

        // 2. 创建密码模式认证请求
        // 封装相关参数
        Map<String, String> parameters = new HashMap<>(4);
        parameters.put("clientId", clientId);
        parameters.put(OAuth2Utils.GRANT_TYPE, "password");
        parameters.put("username", username);
        parameters.put("password", password);
        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);
        TokenRequest tokenRequest = conf.getEndpointsConfigurer().getOAuth2RequestFactory().createTokenRequest(parameters, authenticatedClient);

        // 3. 调用令牌发放器，颁发令牌
        OAuth2AccessToken token = conf.getEndpointsConfigurer().getTokenGranter().grant(tokenRequest.getGrantType(), tokenRequest);
        if (token == null) {
            throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
        }
        // 4. 返回给客户端
        return token;
    }

    /**
     * 对客户端信息进行认证
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, () -> this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports", "Only UsernamePasswordAuthenticationToken is supported"));
        try {
            // 1. 根据clientId 查询数据库客户端信息
            String clientId = authentication.getName();
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            String clientSecret = clientDetails.getClientSecret();
            // 2. 校验密码
            additionalAuthenticationChecks(clientDetails, authentication);
            // 3. 校验密码通过 则创建认证成功对象
            UserDetails user = new User(clientId, clientSecret, clientDetails.getAuthorities());
            return createSuccessAuthentication(clientId, authentication, user);
        } catch (UsernameNotFoundException | NoSuchClientException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e);
        }
    }

    /**
     * 创建认证成功的认证信息
     */
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal, authentication.getCredentials(), this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        log.debug("Authenticated user");
        return result;
    }

    /**
     * 校验密码
     */
    protected void additionalAuthenticationChecks(ClientDetails clientDetails, Authentication authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if (!passwordEncoder.matches(presentedPassword, clientDetails.getClientSecret())) {
                log.debug("Failed to authenticate since password does not match stored value");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
    }
}
