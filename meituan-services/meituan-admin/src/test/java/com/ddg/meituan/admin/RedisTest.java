package com.ddg.meituan.admin;

import cn.torna.swaggerplugin.SwaggerPlugin;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
public class RedisTest {

    @Test
    public void swaggerTest(){
        SwaggerPlugin.pushDoc();
    }
}
