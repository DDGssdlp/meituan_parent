package com.ddg.meituan.authserver.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description: WebSecurity 安全配置：
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/1 09:59
 * @email: wangzhijie0908@gmail.com
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll().and()
                //.authorizeRequests().antMatchers("/admin/**").permitAll().and()
                // 将获取公钥接口进行暴露
                .authorizeRequests().antMatchers("/rsa/publicKey").permitAll()
                .anyRequest().authenticated();
    }
    // 可以在这里创建内存用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
