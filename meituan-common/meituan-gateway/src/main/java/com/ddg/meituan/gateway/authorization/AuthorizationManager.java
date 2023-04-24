package com.ddg.meituan.gateway.authorization;


import com.alibaba.fastjson.JSON;
import com.ddg.meituan.base.constant.BaseConstant;
import com.ddg.meituan.gateway.config.IgnoreUrlsConfig;
import com.ddg.meituan.gateway.domain.UserDto;
import com.nimbusds.jose.JWSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Description: 使用gateway鉴权管理器，用于判断是否有资源的访问权限
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/29 17:35
 * @email: wangzhijie0908@gmail.com
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final StringRedisTemplate redisTemplate;
    private final IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    public AuthorizationManager(StringRedisTemplate redisTemplate,
                                IgnoreUrlsConfig ignoreUrlsConfig) {
        this.redisTemplate = redisTemplate;
        this.ignoreUrlsConfig = ignoreUrlsConfig;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径直接放行
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        //对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        //不同用户体系登录不允许互相访问
        try {
            String token = request.getHeaders().getFirst(BaseConstant.JWT_TOKEN_HEADER);
            if (StringUtils.isEmpty(token)) {
                return Mono.just(new AuthorizationDecision(false));
            }
            String realToken = token.replace(BaseConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            UserDto userDto = JSON.parseObject(userStr, UserDto.class);
            final boolean match = pathMatcher.match(BaseConstant.ADMIN_URL_PATTERN, uri.getPath()) ||
                    pathMatcher.match(BaseConstant.APP_URL_PATTERN, uri.getPath()) ||
                    pathMatcher.match(BaseConstant.SYS_URL_PATTERN, uri.getPath());
            // 前端的用户不能获取后端的接口
            if (BaseConstant.PORTAL_CLIENT_ID.equals(userDto.getClientId()) && match) {
                return Mono.just(new AuthorizationDecision(false));
            }
        } catch (ParseException e) {
            return Mono.just(new AuthorizationDecision(false));
        }
        //其余需要校验权限
        //认证通过且角色匹配的用户可访问当前路径
        //从Redis中获取当前路径可访问角色列表  使用 redis 进行的是路径粒度 和 权限的区分
        String roles = (String) redisTemplate.opsForHash().get(BaseConstant.RESOURCE_ROLES_MAP, uri.getPath());
        // 这里方便测试 先放开
        if (StringUtils.isEmpty(roles)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        List<String> authorities = JSON.parseArray(roles, String.class);
        authorities = authorities.stream().map(i -> i = BaseConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());


        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));

    }

}
