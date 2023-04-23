package com.ddg.meituan.authserver.feign.fallback;

import com.ddg.meituan.base.domain.UserDto;
import com.ddg.meituan.authserver.feign.AdminFeignService;
import com.ddg.meituan.base.api.CommonResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
 * @date 2021/11/23 11:21
 * @email: wangzhijie0908@gmail.com
 */
@Component
@Slf4j
public class AdminFeignFallBackFactory implements FallbackFactory<AdminFeignService> {


    @Override
    public AdminFeignService create(Throwable cause) {
        return (username, uuid) -> CommonResult.success(new UserDto("zhangsan", "$2a$10$YFViWApg0IsGKnZMeYXIi" +
                ".6nlJ2NTXhabxEtqj6QZEiKH8PcGsNkO", 1, 1L));
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(cryptPasswordEncoder.encode("123456"));
    }
}
