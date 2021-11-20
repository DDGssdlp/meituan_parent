package com.ddg.meituan.authserver.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.ddg.meituan.authserver.constant.MessageConstant;
import com.ddg.meituan.authserver.feign.AdminFeignService;
import com.ddg.meituan.authserver.feign.MemberFeignService;

import com.ddg.meituan.common.constant.AuthConstant;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    private List<UserDto> userList;

    private final AdminFeignService adminFeignService;

    private final MemberFeignService memberFeignService;

    @Autowired
    private final HttpServletRequest request;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, AdminFeignService adminFeignService, MemberFeignService memberFeignService, HttpServletRequest request) {
        this.passwordEncoder = passwordEncoder;
        this.adminFeignService = adminFeignService;
        this.memberFeignService = memberFeignService;
        this.request = request;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String clientId = request.getParameter("client_id");
        String code = request.getParameter("code");
        UserDto userDto = null;
        if(AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){

            String uuid = request.getParameter("uuid");
            userDto = adminFeignService.loadUserByUsername(username);
        }else{
            userDto = memberFeignService.loadUserByUsername(username);
        }


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
