package com.ddg.meituan.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Description: Spring Aop 和 redis 实现自定义注解缓存
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/29 16:34
 * @email: wangzhijie0908@gmail.com
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
    String redisKey();
    String timeOut() default "86400";  //超时时间， 单位TimeUnit.SECONDS
    Class<Object> resClass();    //返回数据类型
    boolean isList() default false;   //返回数据是否是List
    String lockName() default ""; // 分布式锁的名称：默认为空
}
