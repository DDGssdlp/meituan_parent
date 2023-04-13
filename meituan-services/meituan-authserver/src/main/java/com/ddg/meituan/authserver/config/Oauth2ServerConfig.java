package com.ddg.meituan.authserver.config;

import com.ddg.meituan.authserver.component.JwtTokenEnhancer;
import com.ddg.meituan.authserver.filter.CustomClientCredentialsTokenEndpointFilter;
import com.ddg.meituan.authserver.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description: 开启 授权服务器的 关自动配置类
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/3/4 14:03
 * @email: wangzhijie0908@gmail.com
 */

@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 加密器
     */
    private final PasswordEncoder passwordEncoder;
    /**
     * 加载用户信息
     */
    private final CustomUserDetailsService userDetailsService;
    /**
     * 认证管理器
     */
    private final AuthenticationManager authenticationManager;
    /**
     * jwt内容增强器用于存储用户id
     */
    private final JwtTokenEnhancer jwtTokenEnhancer;

    private final SecurityClientConfigurationProperties properties;


    /**
     * 自定义 invalid_client Bad client credentials
     */
    private final AuthenticationEntryPoint resultAuthenticationEntryPoint;

    public Oauth2ServerConfig(PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtTokenEnhancer jwtTokenEnhancer, SecurityClientConfigurationProperties properties, AuthenticationEntryPoint resultAuthenticationEntryPoint) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
        this.properties = properties;
        this.resultAuthenticationEntryPoint = resultAuthenticationEntryPoint;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        SecurityClientConfigurationProperties.JwtProperties jwt = properties.getJwt();
        clients.inMemory()
                // 后台管理
                .withClient(jwt.getAdminAppName())
                .secret(passwordEncoder.encode(jwt.getKeyPairPassword()))
                .scopes(jwt.getScopes())
                .authorizedGrantTypes(jwt.getAdminAuthorizedGrantTypes())
                .accessTokenValiditySeconds(jwt.getTokenExpire())
                .refreshTokenValiditySeconds(jwt.getRefreshTokenExpire())
                .and()
                // 前端门户
                .withClient(jwt.getPortalAppName())
                .secret(passwordEncoder.encode(jwt.getKeyPairPassword()))
                .scopes(jwt.getScopes())
                .authorizedGrantTypes(jwt.getPortalAuthorizedGrantTypes())
                .accessTokenValiditySeconds(jwt.getTokenExpire())
                .refreshTokenValiditySeconds(jwt.getRefreshTokenExpire());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        //配置JWT的内容增强器
        enhancerChain.setTokenEnhancers(delegates);
        endpoints.tokenGranter(this.tokenGranter(endpoints));
        endpoints.authenticationManager(authenticationManager)
                //配置加载用户信息的服务
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter()).tokenEnhancer(enhancerChain);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) {
        CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter(securityConfigurer);
        endpointFilter.afterPropertiesSet();
        endpointFilter.setAuthenticationEntryPoint(resultAuthenticationEntryPoint);
        // 客户端认证之前的过滤器
        securityConfigurer.addTokenEndpointAuthenticationFilter(endpointFilter);
        // 如果使用自定义的过滤器不要在使用 allowFormAuthenticationForClients
        //securityConfigurer.allowFormAuthenticationForClients();
    }

    /**
     * accessToken 转换器 jks公钥
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    /**
     * 从classpath下的证书中获取秘钥对
     *
     * @return
     */
    @Bean
    public KeyPair keyPair() {
        String keyPairPassword = properties.getJwt().getKeyPairPassword();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), keyPairPassword.toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", keyPairPassword.toCharArray());
    }

    /**
     * 这是从spring 的代码中 copy出来的, 默认的几个TokenGranter, 还原封不动加进去.
     * 主要目的是覆盖原来的List<TokenGranter>,方便我们添加自定义的授权方式,比如SMSCodeTokenGranter短信验证码授权
     */
    private List<TokenGranter> getTokenGranters(AuthorizationServerEndpointsConfigurer endpoints) {
        AuthorizationServerTokenServices tokenServices = endpoints.getDefaultAuthorizationServerTokenServices();
        AuthorizationCodeServices authorizationCodeServices = endpoints.getAuthorizationCodeServices();

        OAuth2RequestFactory requestFactory = endpoints.getOAuth2RequestFactory();
        List<TokenGranter> tokenGranters = new ArrayList<>();
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, endpoints.getClientDetailsService(), requestFactory));
        tokenGranters.add(new RefreshTokenGranter(tokenServices, endpoints.getClientDetailsService(), requestFactory));
        tokenGranters.add(new ImplicitTokenGranter(tokenServices, endpoints.getClientDetailsService(), requestFactory));
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, endpoints.getClientDetailsService(), requestFactory));
        if (Objects.nonNull(authenticationManager)) {
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(), requestFactory));
        }
        // 这里就是我们自己的授权验证
        tokenGranters.add(new SmsCodeTokenGranter(userDetailsService, tokenServices, endpoints.getClientDetailsService(), requestFactory));
        // 再有其他的验证, 就往下面添加....
        return tokenGranters;
    }

    /**
     * 自定义TokenGranter
     */
    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        return new TokenGranter() {
            private CompositeTokenGranter delegate;
            @Override
            public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
                if (delegate == null) {
                    delegate = new CompositeTokenGranter(getTokenGranters(endpoints));
                }
                return delegate.grant(grantType, tokenRequest);
            }
        };
    }

}
