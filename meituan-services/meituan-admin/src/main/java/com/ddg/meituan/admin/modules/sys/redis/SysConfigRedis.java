package com.ddg.meituan.admin.modules.sys.redis;


import com.alibaba.fastjson.JSON;
import com.ddg.meituan.admin.common.utils.RedisKeys;
import com.ddg.meituan.admin.modules.sys.entity.SysConfigEntity;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author DELL
 */
@Component
public class SysConfigRedis {

    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    private final StringRedisTemplate redisTemplate;

    public SysConfigRedis(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveOrUpdate(SysConfigEntity config) {
        if(config == null){
            return ;
        }
        String key = RedisKeys.getSysConfigKey(config.getParamKey());
        redisTemplate.opsForValue().set(key, JSON.toJSONString(config), DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisTemplate.delete(key);
    }

    public SysConfigEntity get(String configKey){
        String key = RedisKeys.getSysConfigKey(configKey);
        String configStr = redisTemplate.opsForValue().get(key);
        SysConfigEntity sysConfigEntity = null;
        if(!StringUtils.isEmpty(configStr)){
            sysConfigEntity = JSON.parseObject(configStr, SysConfigEntity.class);
        }
        return sysConfigEntity;
    }
}
