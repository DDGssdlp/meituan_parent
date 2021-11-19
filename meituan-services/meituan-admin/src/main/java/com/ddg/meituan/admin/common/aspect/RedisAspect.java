

package com.ddg.meituan.admin.common.aspect;


import com.ddg.meituan.common.exception.MeituanSysException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Redis切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Configuration
@Slf4j
public class RedisAspect {


    @Value("${spring.redis.open: false}")
    private boolean open;

    @Around("execution(* com.ddg.meituan.admin.common.utils.RedisKeys.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                log.error("redis error", e);
                throw new MeituanSysException("Redis服务异常");
            }
        }
        return result;
    }
}
