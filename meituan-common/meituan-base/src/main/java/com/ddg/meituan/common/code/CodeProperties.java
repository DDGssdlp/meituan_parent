package com.ddg.meituan.common.code;

import lombok.Data;
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

@Data
@Component
@ConfigurationProperties(prefix = "code")
public class CodeProperties {
    public  List<CodeConfig> custom;
}
