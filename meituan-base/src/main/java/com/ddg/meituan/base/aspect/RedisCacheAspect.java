package com.ddg.meituan.base.aspect;

import com.alibaba.fastjson.JSON;
import com.ddg.meituan.base.annotation.cache.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Description: 设置切面实现注解缓存
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
@Slf4j
@Component
@Aspect
public class RedisCacheAspect {

    private final StringRedisTemplate stringRedisTemplate;

    private final RedissonClient redissonClient;

    /**
     *  默认不进行空值缓存
     */
    @Value("${meituan.rediscache.cachenull:false}")
    private boolean cacheNull;

    @Autowired
    public RedisCacheAspect(StringRedisTemplate stringRedisTemplate, RedissonClient redissonClient) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redissonClient = redissonClient;
    }

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.ddg.meituan.base.annotation.cache.RedisCache)")
    public void redisCachePointCut() {
    }

    /**
     *  使用环绕通知进行 redis 的缓存
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("redisCachePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RedisCache redisCache = method.getAnnotation(RedisCache.class);
        try {
            if (redisCache != null) {
                // 注解上的描述

                String redisKey = redisCache.redisKey();
                String lockName = redisCache.lockName();
                Integer timeOut = Integer.parseInt(redisCache.timeOut());
                Class<?> resultObjClass = redisCache.resClass();
                boolean isList = redisCache.isList();
                //获取redis数据
                String realValue = stringRedisTemplate.opsForValue().get(redisKey);
                if (!StringUtils.isEmpty(realValue)) {
                    //如果是list类型的返回数据
                    if (isList) {
                        return JSON.parseArray(realValue, resultObjClass);

                    }
                    return JSON.parseObject(realValue, resultObjClass);
                } else {
                    // 并发 需要使用分布式锁加锁：
                    Object result;
                    if (!StringUtils.isEmpty(lockName)) {
                        RLock lock = redissonClient.getLock(lockName);
                        lock.lock();
                        try {
                            result = getAndCacheResult(point, redisKey, timeOut);
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        result = getAndCacheResult(point, redisKey, timeOut);
                    }

                    return result;
                }
            }
        } catch (Exception e) {
            log.error("RedisCacheAspect redisCachePointCutError!", e);
            //异常直接执行方法
            return point.proceed();
        }
        return null;
    }

    private Object getAndCacheResult(ProceedingJoinPoint point, String redisKey, Integer timeOut) throws Throwable {
        Object result;
        String realValue;
        result = point.proceed();
        realValue = JSON.toJSONString(result);
        // 配置是否是可以缓存空值：要是 list类型的数据返回空List 返回的可能是"[]"
        if (cacheNull || (!"[]".equals(realValue) && !StringUtils.isEmpty(realValue))) {
            stringRedisTemplate.opsForValue().set(redisKey, realValue, timeOut, TimeUnit.SECONDS);
        }
        return result;
    }
}
