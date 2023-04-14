package com.ddg.meituan.thridparty.constant;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author 13060
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2023/4/12 11:12
 * @email: wangzhijie0908@gmail.com
 */
public class Constant {

    /**
     *  升序
     */
    public static final String ASC = "asc";

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private final int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
