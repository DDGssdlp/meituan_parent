package com.ddg.meituan.gateway.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
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
@Slf4j
public class IgnoreUrlsConfig implements InitializingBean {
    private List<String> urls;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("IgnoreUrlsConfig afterPropertiesSet urls = {}", urls);
    }
}
