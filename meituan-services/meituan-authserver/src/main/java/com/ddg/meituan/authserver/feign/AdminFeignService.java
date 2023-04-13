package com.ddg.meituan.authserver.feign;

import com.ddg.meituan.authserver.domain.UserDto;
import com.ddg.meituan.authserver.feign.fallback.AdminFeignFallBackFactory;
import com.ddg.meituan.base.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/11/19 17:21
 * @email: wangzhijie0908@gmail.com
 */
@FeignClient(value = "meituan-admin", fallbackFactory = AdminFeignFallBackFactory.class, qualifier = "adminFeignService")
public interface AdminFeignService {

    /**
     * @param username
     * @param code
     * @param uuid
     * @return
     */
    @GetMapping("/sys/login/loadByUsername")
    CommonResult<UserDto> loadUserByUsername(@RequestParam String username, @RequestParam String code, @RequestParam String uuid);
}
