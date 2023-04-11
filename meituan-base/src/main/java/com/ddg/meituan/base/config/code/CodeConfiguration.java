package com.ddg.meituan.base.config.code;

import com.ddg.meituan.base.dynamiccode.DynamicEnumUtils;
import com.ddg.meituan.base.enums.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * Description: code 配置
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
@Configuration
public class CodeConfiguration {

    @Autowired(required = false)
    private CodeProperties codeProperties;

    private final String valuePattern = "^[A-Z][A-Z_0-9]*";

    private final String codePattern = "^[A-Z][0-9]*";

    @Bean
    public List<CodeProperties.CodeConfig> customCodeConfiguration() {
        List<CodeProperties.CodeConfig> codeList = new ArrayList<>();
        if (codeProperties == null) {
            return codeList;
        }
        List<CodeProperties.CodeConfig> custom = codeProperties.getCustom();
        if (custom == null || custom.size() == 0) {
            return codeList;
        }
        Set<String> nameSet = new HashSet<>();
        Code[] codes = Code.values();
        Arrays.stream(codes).forEach(e -> {
            String value = e.getValue();
            nameSet.add(value.toUpperCase());
        });
        custom.forEach(e -> {
            String name = e.getName();
            String code = e.getCode();
            String message = e.getMessage();
            if (name == null || code == null || message == null) {
                throw new RuntimeException("错误码配置错误");
            }
            name = name.toUpperCase();
            if (nameSet.contains(name.toUpperCase())) {
                throw new RuntimeException("含有重复错误码" + name);
            }
            nameSet.add(name.toUpperCase());
            if (!name.matches(valuePattern)) {
                throw new RuntimeException("错误码value不合规，name=" + name);
            }
            if (!code.matches(codePattern)) {
                throw new RuntimeException("错误码code不合规，code=" + code);
            }
            codeList.add(e);
            DynamicEnumUtils.addEnum(Code.class, name.toUpperCase(), new Class[]{
                    String.class, String.class}, new Object[]{
                    code, message});
        });
        return codeList;
    }
}
