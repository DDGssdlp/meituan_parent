package com.ddg.meituan.authserver.feign;

import com.ddg.meituan.authserver.vo.MemberRegisterVo;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

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
@FeignClient("meituan-member")
public interface MemberFeignService {

    @PostMapping("/member/member/register")
    R register(@RequestBody MemberRegisterVo memberRegisterVo) throws MeituanSysException;

    @PostMapping("/member/member/login")
    R login(@RequestBody MemberRegisterVo memberRegisterVo) throws MeituanSysException;

}
