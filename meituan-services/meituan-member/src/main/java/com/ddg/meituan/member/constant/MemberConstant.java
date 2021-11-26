package com.ddg.meituan.member.constant;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/1 10:30
 * @email: wangzhijie0908@gmail.com
 */
public interface MemberConstant {

    String USERNAME_COLUMN = "username";

    String MOBILE_COLUMN = "mobile";

    String PASSWORD_COLUMN = "password";

    String DEFAULT_PASSWORD = "111111";

    String REDIS_CACHE_LOGIN_USER_KEY = "redis_cache_user";

    String REDIS_PHONE_CODE_PREFIX = "redis:phone:code_";

    int MEMBER_CODE_LENGTH = 8;

    String PHONE_CODE_MOCK = "111111";

    String MEITUAN_USERNAME_PREFIX = "meituan_";

    String LOGIN_USER = "login_user";
}
