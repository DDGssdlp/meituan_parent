package com.ddg.meituan.authserver.feign;

import com.ddg.meituan.authserver.domain.UserDto;
import com.ddg.meituan.authserver.feign.fallback.MemberFeignFallBack;
import com.ddg.meituan.base.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/1 10:12
 * @email: wangzhijie0908@gmail.com
 */
@FeignClient(value = "meituan-member", fallback = MemberFeignFallBack.class, qualifier = "memberFeignService")
public interface MemberFeignService {

    @GetMapping("/member/member/loadByUsername")
    CommonResult<UserDto> loadUserByUsername(@RequestParam String username);

}


