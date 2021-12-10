package com.ddg.meituan.member;


import cn.torna.swaggerplugin.SwaggerPlugin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
        SwaggerPlugin.pushDoc();
    }

}

