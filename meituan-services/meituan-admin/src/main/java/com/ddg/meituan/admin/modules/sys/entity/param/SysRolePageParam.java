package com.ddg.meituan.admin.modules.sys.entity.param;

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
 * @date 2021/11/24 15:49
 * @email: wangzhijie0908@gmail.com
 */
@Data
public class SysRolePageParam extends PageParam {

    private Long createUserId;

    private String roleName;
}
