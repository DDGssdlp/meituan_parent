package com.ddg.meituan.common.dynamiccode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/4/27 12:27
 * @email: wangzhijie0908@gmail.com
 */

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "code")
public class CodeProperties implements InitializingBean {
    public  List<CodeConfig> custom;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("动态配置状态码 CodeProperties custom= {}", custom);
    }
}
