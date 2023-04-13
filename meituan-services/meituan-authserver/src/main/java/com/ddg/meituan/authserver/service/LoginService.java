package com.ddg.meituan.authserver.service;

import com.ddg.meituan.authserver.domain.vo.MemberRegisterVo;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.exception.MeituanSysException;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/1 09:59
 * @email: wangzhijie0908@gmail.com
 */
public interface LoginService {

     CommonResult register(MemberRegisterVo memberRegisterVo) throws MeituanSysException;

     CommonResult login(MemberRegisterVo memberRegisterVo);
}
