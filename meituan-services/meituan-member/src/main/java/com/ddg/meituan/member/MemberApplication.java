package com.ddg.meituan.member;

import cn.torna.swaggerplugin.SwaggerPlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/28 17:11
 * @email:
 */
@SpringBootApplication(scanBasePackages = "com.ddg.meituan")
@EnableDiscoveryClient
@EnableFeignClients
public class MemberApplication {

    public static void main(String[] args) {

        SpringApplication.run(MemberApplication.class, args);
    }
}
