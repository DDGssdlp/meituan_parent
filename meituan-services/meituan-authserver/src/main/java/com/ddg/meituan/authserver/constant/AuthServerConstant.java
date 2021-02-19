package com.ddg.meituan.authserver.constant;

/**
 * Description: AuthServer 服务常量
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/31 17:24
 * @email: wangzhijie0908@gmail.com
 */
public interface AuthServerConstant {

    Long STORE_TIME = 5L;

    Long ONE_MIN  = 60L * 1000;

    String REDIS_PHONE_CODE_PREFIX = "redis_phone_code_";

    String REDIS_CACHE_LOGIN_USER_KEY = "redis_cache_user";

    String PHONE_CODE_MOCK = "111111";

    String LOGIN_USER = "login_user";




}
