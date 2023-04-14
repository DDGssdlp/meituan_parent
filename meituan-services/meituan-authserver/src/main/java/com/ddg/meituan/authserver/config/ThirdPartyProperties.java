package com.ddg.meituan.authserver.config;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/22 18:44
 * @email: wangzhijie0908@gmail.com
 */
@Component
@ConfigurationProperties("meituan")
@Data
@Slf4j
public class ThirdPartyProperties implements InitializingBean {

    Map<String, ThirdPartyEntity> thirdparty;

    @Override
    public void afterPropertiesSet() {
        log.info("第三方登录：" +  JSON.toJSONString(thirdparty));
    }


    @Data
    public static class ThirdPartyEntity{
        private String clientId;
        private String clientSecret;
        private String callbackUri;
        private String tokenUri;
        private String userUri;
    }

    @Data
    public static class JsonRootBean {

        private String accessToken;
        private String tokenType;
        private long expiresIn;
        private String refreshToken;
        private String scope;
        private long createdAt;

    }

    @Data
    public static class UserInfo {

        private String name;
        private String avatarUrl;
    }
}



