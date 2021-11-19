

package com.ddg.meituan.admin.common.annotation.validator;

import com.ddg.meituan.common.exception.MeituanSysException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new MeituanSysException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new MeituanSysException(message);
        }
    }
}
