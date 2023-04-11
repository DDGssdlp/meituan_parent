package com.ddg.meituan.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;


/**
 * Description: Spring Security 密码加密器
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/2 09:37
 * @email: wangzhijie0908@gmail.com
 */
@Configuration
public class SpringBeanConfig {
    /**
     *  Spring Security 中密码加密器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *  Spring 进行路径匹配
     * @return
     */
    @Bean
    public PathMatcher pathMatcher(){
        return new AntPathMatcher();
    }
}
