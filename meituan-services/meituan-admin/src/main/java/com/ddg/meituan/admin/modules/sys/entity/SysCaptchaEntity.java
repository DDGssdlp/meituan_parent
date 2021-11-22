

package com.ddg.meituan.admin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 系统验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_captcha")
public class SysCaptchaEntity {

    @TableId(type = IdType.AUTO)
    private Long id;


    private String uuid;
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

}
