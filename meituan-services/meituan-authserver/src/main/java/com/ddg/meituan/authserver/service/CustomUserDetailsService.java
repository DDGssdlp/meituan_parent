package com.ddg.meituan.authserver.service;


import com.ddg.meituan.authserver.constant.AuthServerConstant;
import com.ddg.meituan.authserver.constant.MessageConstant;
import com.ddg.meituan.authserver.domain.SecurityUser;
import com.ddg.meituan.authserver.feign.AdminFeignService;
import com.ddg.meituan.authserver.feign.MemberFeignService;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.domain.UserDto;
import com.ddg.meituan.base.enums.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * Description: 用户管理业务类
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/3/4 13:19
 * @email: wangzhijie0908@gmail.com
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private AdminFeignService adminFeignService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private MemberFeignService memberFeignService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String clientId = request.getParameter("client_id");
        CommonResult<UserDto> userDtoCommonResult = null;
        try {
            if (AuthServerConstant.ADMIN_CLIENT_ID.equals(clientId)) {
                String uuid = request.getParameter("uuid");
                userDtoCommonResult = adminFeignService.loadUserByUsername(username, uuid);
            } else {
                userDtoCommonResult = memberFeignService.loadUserByUsername(username);
            }

        } catch (Exception e) {
            log.error("UserDto 获取错误 error = {}", e.getMessage());

        }
        if (Objects.isNull(userDtoCommonResult) || Objects.isNull(userDtoCommonResult.getData())) {
            throw new InvalidGrantException(MessageConstant.SERVER_ERROR);
        }

        if (!Code.NO_PROBLEM.getValue().equals(userDtoCommonResult.getCode())) {
            throw new InvalidGrantException(userDtoCommonResult.getMessage());
        }
        UserDto user = userDtoCommonResult.getData();
        user.setId(1L);

        user.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(user);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

    public UserDetails loadUserByUsernameAndSmsCode(String phone, String code) throws UsernameNotFoundException {
        SecurityUser zhangsan = new SecurityUser(new UserDto("zhangsan", null, 1, 1L));
        zhangsan.setPhone("1231231313");
        zhangsan.setId(1L);
        return zhangsan;
    }

}
