package com.ddg.meituan.authserver.component;

import com.alibaba.fastjson.JSON;
import com.ddg.meituan.base.api.CommonResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author 13060
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2023/4/13 11:04
 * @email: wangzhijie0908@gmail.com
 */
@Component
public class ResultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().print(JSON.toJSONString(CommonResult.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}