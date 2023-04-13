package com.ddg.meituan.authserver.feign.fallback;

import com.ddg.meituan.authserver.domain.UserDto;
import com.ddg.meituan.authserver.feign.AdminFeignService;
import com.ddg.meituan.base.api.CommonResult;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/11/23 11:21
 * @email: wangzhijie0908@gmail.com
 */
//@Component
public class AdminFeignFallBack implements AdminFeignService {
    @Override
    public CommonResult<UserDto> loadUserByUsername(String username, String uuid) {
        return CommonResult.success(new UserDto("zhangsan", "1", 1));
    }
}
