package com.ddg.meituan.authserver.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ddg.meituan.authserver.constant.AuthServerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源与角色匹配关系管理业务类
 * Created by macro on 2020/6/19.
 */
@Service
public class ResourceServiceImpl {

    private Map<String, String> resourceRolesMap;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public ResourceServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello", "ADMIN");
        resourceRolesMap.put("/api/user/currentUser", "ADMIN,TEST");
        redisTemplate.opsForHash().putAll(AuthServerConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
