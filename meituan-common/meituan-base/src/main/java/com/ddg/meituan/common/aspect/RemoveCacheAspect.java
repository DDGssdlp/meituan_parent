package com.ddg.meituan.common.aspect;

import com.ddg.meituan.common.annotation.RedisCache;
import com.ddg.meituan.common.annotation.RemoveCache;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/30 16:09
 * @email: wangzhijie0908@gmail.com
 */
@Component
@Aspect
@Slf4j
public class RemoveCacheAspect {

    private final StringRedisTemplate stringRedisTemplate;  //注入redis模板

    @Autowired
    public RemoveCacheAspect(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Pointcut("@annotation(com.ddg.meituan.common.annotation.RemoveCache)") //定义切点
    public void removeCachePointCut() {
    }

    @Around("removeCachePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RemoveCache removeCache = method.getAnnotation(RemoveCache.class);
        try {
            if (removeCache != null) {
                // 注解上的描述
                String[] value = removeCache.value();
                // 从获取redis数据
                for (String key : value) {
                    stringRedisTemplate.delete(key);
                }
                return point.proceed();
            }
        } catch (Exception e) {
            log.error("removeCacheError!", e);
            return point.proceed();
        }
        return null;
    }
}
