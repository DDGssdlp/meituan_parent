package com.ddg.meituan.gateway.config;

import com.ddg.meituan.base.constant.BaseConstant;
import com.ddg.meituan.gateway.authorization.AuthorizationManager;
import com.ddg.meituan.gateway.component.RestAuthenticationEntryPoint;
import com.ddg.meituan.gateway.component.RestfulAccessDeniedHandler;
import com.ddg.meituan.gateway.filter.IgnoreUrlsRemoveJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;


/**
 * Description: 资源服务器配置
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/3/4 15:27
 * @email: wangzhijie0908@gmail.com
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final AuthorizationManager authorizationManager;
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

    @Autowired
    public ResourceServerConfig(AuthorizationManager authorizationManager,
                                IgnoreUrlsConfig ignoreUrlsConfig,
                                RestfulAccessDeniedHandler restfulAccessDeniedHandler,
                                RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                                IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter) {
        this.authorizationManager = authorizationManager;
        this.ignoreUrlsConfig = ignoreUrlsConfig;
        this.restfulAccessDeniedHandler = restfulAccessDeniedHandler;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.ignoreUrlsRemoveJwtFilter = ignoreUrlsRemoveJwtFilter;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer()
                .jwt()
                    .jwtAuthenticationConverter(jwtAuthenticationConverter());
                //自定义处理JWT请求头过期或签名错误的结果
                /*.and()
                    .accessDeniedHandler(restfulAccessDeniedHandler)
                    .authenticationEntryPoint(restAuthenticationEntryPoint);*/
        //对白名单路径，直接移除JWT请求头
        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.authorizeExchange()
                //白名单配置
                .pathMatchers(ignoreUrlsConfig.getUrls().toArray(new String[0])).permitAll()
                //鉴权管理器配置
                .anyExchange()
                    .access(authorizationManager)
                    .and()
                .exceptionHandling()
                    //处理未授权
                    .accessDeniedHandler(restfulAccessDeniedHandler)
                    //处理未认证
                    .authenticationEntryPoint(restAuthenticationEntryPoint)
                    .and()
                .csrf()
                    .disable();
        return http.build();
    }

    /**
     * ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication，需要把jwt的Claim中的authorities加入
     * 解决方案：重新定义ReactiveAuthenticationManager权限管理器，默认转换器JwtGrantedAuthoritiesConverter
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(BaseConstant.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(BaseConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}

