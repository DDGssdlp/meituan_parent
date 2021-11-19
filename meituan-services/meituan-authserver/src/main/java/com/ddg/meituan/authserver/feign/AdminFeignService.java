package com.ddg.meituan.authserver.feign;

import com.ddg.meituan.common.domain.UserDto;
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
@FeignClient(value = "meituan-admin")
public interface AdminFeignService {

    @GetMapping("/sys/login/loadByUsername")
    UserDto loadUserByUsername(@RequestParam String username);
}
