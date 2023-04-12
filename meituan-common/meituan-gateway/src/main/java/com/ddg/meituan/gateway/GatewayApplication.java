package com.ddg.meituan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/29 17:33
 * @email: wangzhijie0908@gmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableOpenApi
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
