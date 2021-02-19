package com.ddg.meituan.thridparty.component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: oss 配置信息
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/30 18:00
 * @email: wangzhijie0908@gmail.com
 */
@Component
@Data
@Slf4j
@ConfigurationProperties(prefix = "meituan.oss")
public class OSSConfigurationProperties implements InitializingBean {

    private String endpoint;

    private String keyId;

    private String keySecret;

    private String bucketName;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("oss配置读取完成", toString());
    }
}
