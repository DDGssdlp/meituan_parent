package com.ddg.meituan.authserver.feign.fallback;

import com.ddg.meituan.authserver.domain.UserDto;
import com.ddg.meituan.authserver.domain.vo.MemberRegisterVo;
import com.ddg.meituan.authserver.domain.vo.MemberVo;
import com.ddg.meituan.authserver.feign.MemberFeignService;
import com.ddg.meituan.base.api.CommonResult;
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
 * @date 2021/11/29 16:51
 * @email: wangzhijie0908@gmail.com
 */
@Component
public class MemberFeignFallBack implements MemberFeignService {
    @Override
    public CommonResult<Long> register(MemberRegisterVo memberRegisterVo) {
        return CommonResult.failed();
    }

    @Override
    public CommonResult login(MemberRegisterVo memberRegisterVo) {
        return CommonResult.failed();
    }

    @Override
    public CommonResult<UserDto> loadUserByUsername(String username, String code) {
        return null;
    }

    @Override
    public CommonResult<MemberVo> info(Long id) {
        return CommonResult.failed();
    }
}
