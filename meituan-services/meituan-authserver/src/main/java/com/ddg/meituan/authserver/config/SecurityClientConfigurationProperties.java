package com.ddg.meituan.authserver.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Description:
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
@Component
@Slf4j
@ConfigurationProperties(prefix = "security")
public class SecurityClientConfigurationProperties  {

    private JwtProperties jwt;

    public JwtProperties getJwt() {
        return jwt;
    }

    public void setJwt(JwtProperties jwt) {
        this.jwt = jwt;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        log.info("jwtProperties init -->{}", getJwt().toString());
    }

    @Data
    public static class JwtProperties {

        private Resource keyStore;

        private String keyPairPassword;

        private String adminAppName;

        private String portalAppName;

        private String scopes;

        private Integer tokenExpire;

        private Integer refreshTokenExpire;

        private String[] portalAuthorizedGrantTypes;

        private String[] adminAuthorizedGrantTypes;
    }

}
