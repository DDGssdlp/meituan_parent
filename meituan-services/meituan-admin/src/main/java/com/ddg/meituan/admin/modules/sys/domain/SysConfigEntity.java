

package com.ddg.meituan.admin.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_config")
public class SysConfigEntity {
	@TableId
	private Long id;

	@NotBlank(message="参数名不能为空")
	private String paramKey;

	@NotBlank(message="参数值不能为空")
	private String paramValue;

	private String remark;

	private Integer status;

	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}
