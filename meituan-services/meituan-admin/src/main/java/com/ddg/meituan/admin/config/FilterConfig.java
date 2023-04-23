package com.ddg.meituan.admin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Filter配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class FilterConfig {

    /**
     *  Spring Security 中密码加密器

     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
