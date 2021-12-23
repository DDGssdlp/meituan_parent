package com.ddg.meituan.authserver.feign;

import com.ddg.meituan.authserver.feign.fallback.MemberFeignFallBack;
import com.ddg.meituan.authserver.vo.MemberRegisterVo;
import com.ddg.meituan.authserver.vo.MemberVo;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/member/member/register")
    CommonResult<Long> register(@RequestBody MemberRegisterVo memberRegisterVo);

    @PostMapping("/member/member/login")
    CommonResult login(@RequestBody MemberRegisterVo memberRegisterVo);

    @GetMapping("/member/member/loadByUsername")
    CommonResult<UserDto> loadUserByUsername(@RequestParam String username, @RequestParam String code);

    @GetMapping("/member/member/info/{id}")
    CommonResult<MemberVo> info(@PathVariable("id") Long id);

}


