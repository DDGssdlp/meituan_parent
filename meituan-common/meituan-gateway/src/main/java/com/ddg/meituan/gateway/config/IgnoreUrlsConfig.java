package com.ddg.meituan.gateway.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 网关白名单配置
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/29 17:35
 * @email: wangzhijie0908@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="secure.ignore")
public class IgnoreUrlsConfig implements InitializingBean {
    private List<String> urls;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(urls);
    }
}
