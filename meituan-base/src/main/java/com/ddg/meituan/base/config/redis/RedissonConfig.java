package com.ddg.meituan.base.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Description: redission 配置：
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/3 12:25
 * @email: wangzhijie0908@gmail.com
 */
@Configuration
public class RedissonConfig {

    /**
     * 所有的redis操作都是需要通过redission client对象
     *
     * @return
     * @throws IOException
     */

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private int port;

    @Value("${spring.redis.password:''}")
    private String password;

    private static final int DATABASE = 5;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(){
        Config config = new Config();
        // 目前使用的是单节点模式：需要注意的就是redis地址有问题 redis配置的address 需要使用redis://
        // 或者是rediss://开头 如果redis启动的是ssl安全链接 使用rediss://
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + host + ":" + port + "").setDatabase(DATABASE);
        // 创建实例
        if(!StringUtils.isEmpty(password) && !"''".equals(password)){
            singleServerConfig.setPassword(password);
        }
        return Redisson.create(config);
    }

}
