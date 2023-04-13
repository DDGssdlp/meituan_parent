package com.ddg.meituan.authserver.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author DELL
 */
@Data
public class SocialMemberVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accessToken;

    private String remindIn;

    private int expiresIn;

    private String uid;

    private String isrealname;
}