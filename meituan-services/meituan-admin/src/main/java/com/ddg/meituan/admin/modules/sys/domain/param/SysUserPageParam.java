package com.ddg.meituan.admin.modules.sys.domain.param;

import com.ddg.meituan.base.api.PageParam;
import lombok.Data;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/11/24 13:12
 * @email: wangzhijie0908@gmail.com
 */
@Data
public class SysUserPageParam extends PageParam {

    private Long createUserId;

    private String username;
}
