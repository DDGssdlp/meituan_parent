package com.ddg.meituan.authserver.feign.fallback;

import com.ddg.meituan.authserver.feign.MemberFeignService;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/11/29 16:51
 * @email: wangzhijie0908@gmail.com
 */
@Component
public class MemberFeignFallBack implements MemberFeignService {

    @Override
    public CommonResult<UserDto> loadUserByUsername(String username) {
        return CommonResult.success(new UserDto("zhangsan", "$2a$10$ljbjmzrwLm4Q3GsMzVO2ZeM8mZAXEIoBoxEgGlD6.Uf" +
                ".EGqKdRjqO", 1, 1L, Collections.emptyList()));
    }

}
