package com.ddg.meituan.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @Author: DDG
 * @Date: 2020/5/29 10:37
 * @Description: SessionConfig 类 自定义cookie
 */
//@Configuration
public class SessionConfig {
    /**
     * 自定义cookie
     * */
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setCookiePath("/");
        serializer.setDomainName("gulimall.com");
        return serializer;
    }
    /**
     * 指定Session序列化机制是Json
     * */
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

}
