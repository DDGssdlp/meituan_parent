package com.ddg.meituan.gateway.config;

import com.ddg.meituan.gateway.controlleradvice.GlobalGatewayExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * Description: 重写的 DefaultErrorWebExceptionHandler 优先级一定要小于内置
 * ResponseStatusExceptionHandler 经过它处理的获取对应错误类的 响应码
 * 其他扩展 可以参考 SentinelBlockExceptionHandler sentinel
 * 整合网关的处理，不过整体和默认的异常处理没有什么区别 这里处理得时白名单中得uri 出现了异常 如果是auth 不是这里处理
 * ========================================================================
 * ------------------------------------------------------------------------
 * order 注解中的值越小 越优先执行
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/10/26 14:15
 * @email: wangzhijie0908@gmail.com
 */
//@Configuration
public class GlobalGatewayExceptionConfig {


    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer){
        GlobalGatewayExceptionHandler globalGatewayExceptionHandler =new GlobalGatewayExceptionHandler();
        globalGatewayExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        globalGatewayExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        globalGatewayExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return globalGatewayExceptionHandler;
    }
}

