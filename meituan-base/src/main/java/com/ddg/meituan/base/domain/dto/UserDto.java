package com.ddg.meituan.base.domain.dto;


import lombok.*;

import java.util.List;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/3/4 13:26
 * @email: wangzhijie0908@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<String> roles;

    public UserDto(String username, String password, Integer status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }
}