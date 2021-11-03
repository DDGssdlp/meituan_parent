package com.ddg.meituan.gateway.controlleradvice;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/10/26 14:06
 * @email: wangzhijie0908@gmail.com
 */

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.ddg.meituan.gateway.domain.Code;
import com.ddg.meituan.gateway.domain.CommonResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * Description: 网关异常全局处理 ErrorWebExceptionHandler
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/3/3 16:13
 * @email: wangzhijie0908@gmail.com
 */
@Slf4j
@Data
public class GlobalGatewayExceptionHandler implements ErrorWebExceptionHandler {

    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();
    private List<ViewResolver> viewResolvers = Collections.emptyList();
    private ThreadLocal<CommonResult<Object>> threadLocal=new ThreadLocal<>();


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
        log.error("网关异常全局处理 GlobalGatewayExceptionHandler handle  message = {}",throwable.getMessage());
        //这里只是做个最简单的同一的异常结果输出，实际业务可根据throwable不同的异常处理不同的逻辑
        Throwable cause;
        CommonResult<Object> commonResult = CommonResult.failed(Code.SERVER_ERROR,
                (cause= throwable.getCause()) != null? cause.toString(): throwable.toString());
        threadLocal.set(commonResult);
        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);


        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(throwable))
                .flatMap((handler) -> handler.handle(newRequest))
                .flatMap((response) -> write(exchange, response));
    }
    /**
     * 统一返回指定异常信息(指定json格式输出)
     * @param request
     * @return
     */
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request){
        return ServerResponse.status(HttpStatus.HTTP_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(threadLocal.get()));
    }



    /**
     * 参考DefaultErrorWebExceptionHandler
     */
    private Mono<? extends Void> write(ServerWebExchange exchange, ServerResponse response) {
        exchange.getResponse().getHeaders().setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }

    /**
     * 参考DefaultErrorWebExceptionHandler
     */
    private class ResponseContext implements ServerResponse.Context {
        private ResponseContext() {
        }

        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return GlobalGatewayExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return GlobalGatewayExceptionHandler.this.viewResolvers;
        }
    }
}
