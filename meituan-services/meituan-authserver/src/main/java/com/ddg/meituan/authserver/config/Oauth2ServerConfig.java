package com.ddg.meituan.authserver.config;


import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.ddg.meituan.authserver.component.JwtTokenEnhancer;
import com.ddg.meituan.authserver.filter.MyClientCredentialsTokenEndpointFilter;
import com.ddg.meituan.authserver.service.impl.UserServiceImpl;
import com.ddg.meituan.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

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
    private final UserServiceImpl userDetailsService;
    /**
     *  认证管理器
     */
    private final AuthenticationManager authenticationManager;
    /**
     *  jwt内容增强器用于存储用户id
     */
    private final JwtTokenEnhancer jwtTokenEnhancer;

    private final SecurityClientConfigurationProperties properties;

    @Autowired
    public Oauth2ServerConfig(PasswordEncoder passwordEncoder,
                              UserServiceImpl userDetailsService,
                              AuthenticationManager authenticationManager,
                              JwtTokenEnhancer jwtTokenEnhancer,
                              SecurityClientConfigurationProperties properties) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
        this.properties = properties;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        SecurityClientConfigurationProperties.JwtProperties jwt = properties.getJwt();
        clients.inMemory()
                // 后台管理
                .withClient(jwt.getAdminAppName())
                .secret(passwordEncoder.encode(jwt.getKeyPairPassword()))
                .scopes(jwt.getScopes())
                .authorizedGrantTypes(jwt.getAuthorizedGrantTypes())
                .accessTokenValiditySeconds(jwt.getTokenExpire())
                .refreshTokenValiditySeconds(jwt.getRefreshTokenExpire())
                .and()
                // 前端门户
                .withClient(jwt.getPortalAppName())
                .secret(passwordEncoder.encode(jwt.getKeyPairPassword()))
                .scopes(jwt.getScopes())
                .authorizedGrantTypes(jwt.getAuthorizedGrantTypes())
                .accessTokenValiditySeconds(jwt.getTokenExpire())
                .refreshTokenValiditySeconds(jwt.getRefreshTokenExpire());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        //配置JWT的内容增强器
        enhancerChain.setTokenEnhancers(delegates);
        endpoints.authenticationManager(authenticationManager)
                //配置加载用户信息的服务
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);


    }

    /**
     *  异常自定义处理：
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
        MyClientCredentialsTokenEndpointFilter endpointFilter = new MyClientCredentialsTokenEndpointFilter(security);
        endpointFilter.afterPropertiesSet();
        endpointFilter.setAuthenticationEntryPoint(authenticationEntryPoint());
        security.addTokenEndpointAuthenticationFilter(endpointFilter);

        security.authenticationEntryPoint(authenticationEntryPoint())
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            response.setStatus(HttpStatus.HTTP_OK);
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            CommonResult<?> result = CommonResult.failed(e.getMessage());
            response.getWriter().print(JSONUtil.toJsonStr(result));
            response.getWriter().flush();
        };
    }


    /**
     *  accessToken 转换器 jks公钥
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
     * @return
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }

}
