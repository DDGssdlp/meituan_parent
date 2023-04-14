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

    String RESOURCE_ROLES_MAP = "AUTH:RESOURCE_ROLES_MAP";


    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 后台管理client_id
     */
    String ADMIN_CLIENT_ID = "admin-app";

    /**
     * 前台商城client_id
     */
    String PORTAL_CLIENT_ID = "portal-app";

    /**
     * 后台管理接口路径匹配
     */
    String ADMIN_URL_PATTERN = "/mall-admin/**";

    /**
     * Redis缓存权限规则key
     */
    String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "user";




}
