package com.ddg.meituan.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author DELL
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*
     * 进行访问使用的地址就是 localhost：8001/swagger-ui.html 地址是固定的
     * */

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                //配置的是名字
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                // 这里配置的是有admin就不显示
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .select()
                //这里写的是API接口所在的包位置
                .apis(RequestHandlerSelectors.basePackage("com.ddg.meituan.*.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("美团-vue API文档")
                .description("本文档描述了 美团-vue 微服务接口定义")
                .version("1.0")
                .contact(new Contact("Edison", "http://101.200.140.80", "wangzhijip0908@gmail.com"))
                .build();

    }
}
