package com.ddg.meituan.authserver.service.impl;

import com.ddg.meituan.authserver.constant.AuthServerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源与角色匹配关系管理业务类
 *
 * @author macro
 * @date 2020/6/19
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
        resourceRolesMap.put("/api/product/category/list", "ADMIN");
        resourceRolesMap.put("/api/user/currentUser", "ADMIN,TEST");
        resourceRolesMap.put("/api/thirdparty/minio/upload", "ADMIN");
        resourceRolesMap.put("/api/sys/user/info", "ADMIN");
        redisTemplate.opsForHash().putAll(AuthServerConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
