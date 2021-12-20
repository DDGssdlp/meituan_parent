package com.ddg.meituan.authserver.feign.fallback;

import com.ddg.meituan.authserver.constant.MessageConstant;
import com.ddg.meituan.authserver.feign.AdminFeignService;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.domain.UserDto;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
@Component
@Slf4j
public class AdminFeignFallBackFactory implements FallbackFactory<AdminFeignService> {


    @Override
    public AdminFeignService create(Throwable cause) {
        return (username, code, uuid) -> CommonResult.failed(MessageConstant.USERNAME_PASSWORD_ERROR);
    }
}
