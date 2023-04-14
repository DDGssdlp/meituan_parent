package com.ddg.meituan.base.aspect;

import com.ddg.meituan.base.api.WebLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Description: 统一日志处理切面
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/29 16:36
 * @email: wangzhijie0908@gmail.com
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {


    @Pointcut("execution(public * com.ddg.meituan.*.controller.*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        Object result = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)){
            HttpServletRequest request = attributes.getRequest();
            //记录请求信息(通过Logstash传入Elasticsearch)
            WebLog webLog = new WebLog();
            result = joinPoint.proceed();
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            if (method.isAnnotationPresent(ApiOperation.class)) {
                ApiOperation log = method.getAnnotation(ApiOperation.class);
                webLog.setDescription(log.value());
            }
            long endTime = System.currentTimeMillis();
            String urlStr = request.getRequestURI();
            webLog.setBasePath(urlStr);
            webLog.setIp(request.getRemoteUser());
            webLog.setMethod(request.getMethod());
            webLog.setParameter(getParameter(method, joinPoint.getArgs()));
            webLog.setResult(result);
            webLog.setSpendTime((int) (endTime - startTime));
            webLog.setStartTime(startTime);
            webLog.setUri(request.getRequestURI());
            webLog.setUrl(request.getRequestURL().toString());
            Map<String,Object> logMap = new HashMap<>();
            logMap.put("url",webLog.getUrl());
            logMap.put("method",webLog.getMethod());
            logMap.put("parameter",webLog.getParameter());
            logMap.put("spendTime",webLog.getSpendTime());
            logMap.put("description",webLog.getDescription());
            //LOGGER.info(Markers.appendEntries(logMap), JSONUtil.parse(webLog).toString());
        }
        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }
}
