package com.ddg.meituan.gateway.controlleradvice;


import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.enums.Code;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * Description: 网关异常全局处理 ErrorWebExceptionHandler 处理得非auth异常
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
    private final ThreadLocal<CommonResult<?>> threadLocal = new ThreadLocal<>();


    @Override
    @Nonnull
    public Mono<Void> handle(@Nonnull ServerWebExchange exchange, Throwable throwable) {
        log.error("网关异常全局处理 GlobalGatewayExceptionHandler handle  message = {}", throwable.getMessage());
        //这里只是做个最简单的同一的异常结果输出，实际业务可根据throwable不同的异常处理不同的逻辑
        Throwable cause;

        CommonResult<?> commonResult = CommonResult.failed(Code.SERVER_ERROR,
                (cause = throwable.getCause()) != null ? cause.toString() : throwable.toString());
        threadLocal.set(commonResult);
        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);


        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(throwable))
                .flatMap((handler) -> handler.handle(newRequest))
                .flatMap((response) -> write(exchange, response));
    }

    /**
     * 统一返回指定异常信息(指定json格式输出)
     *
     * @param request
     * @return
     */
    @Nonnull
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        return ServerResponse.status(HttpStatus.OK)
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
        @Nonnull
        public List<HttpMessageWriter<?>> messageWriters() {
            return GlobalGatewayExceptionHandler.this.messageWriters;
        }

        @Override
        @Nonnull
        public List<ViewResolver> viewResolvers() {
            return GlobalGatewayExceptionHandler.this.viewResolvers;
        }
    }
}
