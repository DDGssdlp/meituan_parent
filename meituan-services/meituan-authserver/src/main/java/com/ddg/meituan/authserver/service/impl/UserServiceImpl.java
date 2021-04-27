package com.ddg.meituan.authserver.service.impl;



import cn.hutool.core.collection.CollUtil;
import com.ddg.meituan.authserver.constant.MessageConstant;
import com.ddg.meituan.authserver.feign.MemberFeignService;
import com.ddg.meituan.common.domain.SecurityUser;

import com.ddg.meituan.common.domain.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
public class UserServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final MemberFeignService memberFeignService;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, MemberFeignService memberFeignService) {
        this.passwordEncoder = passwordEncoder;
        this.memberFeignService = memberFeignService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = memberFeignService.loadUserByUsername(username);

        if (userDto == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        SecurityUser securityUser = new SecurityUser(userDto);
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

}
