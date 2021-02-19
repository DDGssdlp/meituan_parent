package com.ddg.meituan.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/8 11:57
 * @email: wangzhijie0908@gmail.com
 */
@Component
@Data
@ConfigurationProperties("meituan.thread")
public class ThreadPoolConfigurationProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;

    private Integer queueSize;
}
