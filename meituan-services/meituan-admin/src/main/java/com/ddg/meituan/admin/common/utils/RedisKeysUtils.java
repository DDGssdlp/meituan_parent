package com.ddg.meituan.admin.common.utils;

/**
 * Redis所有Keys
 *
 * @author Mark sunlightcs@gmail.com
 */
public class RedisKeysUtils {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
