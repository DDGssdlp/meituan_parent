package com.ddg.meituan.authserver.service;

import com.ddg.meituan.authserver.vo.MemberRegisterVo;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.R;

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
 * @date 2021/2/1 09:59
 * @email: wangzhijie0908@gmail.com
 */
public interface LoginService {

     R register(MemberRegisterVo memberRegisterVo) throws MeituanSysException;

     R login(MemberRegisterVo memberRegisterVo);
}
