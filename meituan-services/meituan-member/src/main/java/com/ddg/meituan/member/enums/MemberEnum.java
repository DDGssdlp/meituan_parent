package com.ddg.meituan.member.enums;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/21 09:03
 * @email: wangzhijie0908@gmail.com
 */
public interface MemberEnum {

    enum Status{
        UP(1,"开启"),
        DOWN(2,"关闭"),
        ;
        /**
         * 枚举描述
         */
        public final static String ENUM_DESC = "用户封禁状态 1 开启 2 关闭";
        public Integer code;

        public String desc;

        Status(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

}
