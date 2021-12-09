package com.ddg.meituan.member;

import cn.torna.swaggerplugin.SwaggerPlugin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/28 17:11
 * @email:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberApplicationTest {

    @Test
    public void pushDoc(){
        // 将文档推送到Torna服务中去，默认查找resources下的torna.json
        SwaggerPlugin.pushDoc();
    }

}

