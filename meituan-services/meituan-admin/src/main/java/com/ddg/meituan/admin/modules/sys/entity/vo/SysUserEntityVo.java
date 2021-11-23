package com.ddg.meituan.admin.modules.sys.entity.vo;

import com.ddg.meituan.admin.modules.sys.entity.SysUserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/11/22 17:43
 * @email: wangzhijie0908@gmail.com
 */
@Data
@ApiModel
public class SysUserEntityVo extends SysUserEntity {

    @ApiModelProperty("管理员角色ID列表")
    List<Long> roleIdList;

    @ApiModelProperty("管理员角色列表")
    List<String> roleList;

    public SysUserEntityVo(SysUserEntity sysUserEntity) {
        super.setUserId(sysUserEntity.getUserId());
        super.setCreateTime(sysUserEntity.getCreateTime());
        super.setCreateUserId(sysUserEntity.getCreateUserId());
        super.setEmail(sysUserEntity.getEmail());
        super.setMobile(sysUserEntity.getMobile());
        super.setPassword(sysUserEntity.getPassword());
        super.setSalt(sysUserEntity.getSalt());
        super.setStatus(sysUserEntity.getStatus());
        super.setUsername(sysUserEntity.getUsername());
    }
}
