package com.ddg.meituan.authserver.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Description: spring
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author 13060
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2023/4/13 11:06
 * @email: wangzhijie0908@gmail.com
 */
public class CustomClientCredentialsTokenEndpointFilter extends ClientCredentialsTokenEndpointFilter {

    private final AuthorizationServerSecurityConfigurer configurer;
    private AuthenticationEntryPoint resultAuthenticationEntryPoint;

    public CustomClientCredentialsTokenEndpointFilter(AuthorizationServerSecurityConfigurer configurer) {
        this.configurer = configurer;
    }

    @Override
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.resultAuthenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected AuthenticationManager getAuthenticationManager() {
        return configurer.and().getSharedObject(AuthenticationManager.class);
    }

    @Override
    public void afterPropertiesSet() {
        setAuthenticationFailureHandler((request, response, exception) -> resultAuthenticationEntryPoint.commence(request, response, exception));
        setAuthenticationSuccessHandler((request, response, authentication) -> {
            // no-op - just allow filter chain to continue to token endpoint
        });
    }
}
