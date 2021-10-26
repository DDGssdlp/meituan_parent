package com.ddg.meituan.authserver.constant;

/**
 * 消息常量
 * Created by macro on 2020/6/19.
 */
public interface MessageConstant {

    String LOGIN_SUCCESS = "登录成功!";

    String USERNAME_PASSWORD_ERROR = "用户名或密码错误!";

    String CREDENTIALS_EXPIRED = "该账户的登录凭证已过期，请重新登录!";

    String ACCOUNT_DISABLED = "该账户已被禁用，请联系管理员!";

    String ACCOUNT_LOCKED = "该账号已被锁定，请联系管理员!";

    String ACCOUNT_EXPIRED = "该账号已过期，请联系管理员!";

    String PERMISSION_DENIED = "没有访问权限，请联系管理员!";

}
