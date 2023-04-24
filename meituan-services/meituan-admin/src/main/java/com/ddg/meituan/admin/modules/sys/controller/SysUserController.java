package com.ddg.meituan.admin.modules.sys.controller;

import com.ddg.meituan.admin.common.annotation.SysLog;
import com.ddg.meituan.admin.common.annotation.validator.Assert;
import com.ddg.meituan.admin.common.annotation.validator.ValidatorUtils;
import com.ddg.meituan.admin.constant.Constant;
import com.ddg.meituan.admin.modules.sys.domain.SysUserEntity;
import com.ddg.meituan.admin.modules.sys.domain.param.SysUserPageParam;
import com.ddg.meituan.admin.modules.sys.domain.vo.SysUserEntityVo;
import com.ddg.meituan.admin.modules.sys.form.PasswordForm;
import com.ddg.meituan.admin.modules.sys.service.SysUserRoleService;
import com.ddg.meituan.admin.modules.sys.service.SysUserService;
import com.ddg.meituan.base.annotation.validgroup.AddGroup;
import com.ddg.meituan.base.annotation.validgroup.UpdateGroup;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.constant.BaseConstant;
import com.ddg.meituan.base.domain.UserDto;
import com.ddg.meituan.base.exception.MeituanSysException;
import com.ddg.meituan.base.utils.PageUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    private final SysUserService sysUserService;

    private final SysUserRoleService sysUserRoleService;

    @Autowired
    public SysUserController(SysUserService sysUserService, SysUserRoleService sysUserRoleService) {
        this.sysUserService = sysUserService;
        this.sysUserRoleService = sysUserRoleService;
    }

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<SysUserEntity>> list(SysUserPageParam params,
                                                       @RequestHeader(BaseConstant.USER_TOKEN_HEADER) UserDto userDto) {
        //只有超级管理员，才能查看所有管理员列表
        if (userDto.getId() != Constant.SUPER_ADMIN) {
            params.setCreateUserId(userDto.getId());
        }
        PageUtils<SysUserEntity> page = sysUserService.queryPage(params);

        return CommonResult.success(page);
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    public CommonResult<SysUserEntityVo> info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.getById(userId);
        SysUserEntityVo userEntityVo = new SysUserEntityVo(user);
        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        userEntityVo.setRoleIdList(roleIdList);

        return CommonResult.success(userEntityVo);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody SysUserEntity user, @RequestHeader(BaseConstant.USER_TOKEN_HEADER) UserDto userDto) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        user.setCreateUserId(userDto.getId());
        sysUserService.saveUser(user);

        return CommonResult.success();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody SysUserEntity user, @RequestHeader(BaseConstant.USER_TOKEN_HEADER) UserDto userDto) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        user.setCreateUserId(userDto.getId());
        sysUserService.update(user);

        return CommonResult.success();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] userIds, @RequestHeader(BaseConstant.USER_TOKEN_HEADER) UserDto userDto) {
        if (ArrayUtils.contains(userIds, 1L)) {
            throw new MeituanSysException("管理员用户不能删除");
        }

        if (ArrayUtils.contains(userIds, userDto.getId())) {
            throw new MeituanSysException("当前用户不能被删除");
        }

        sysUserService.deleteBatch(userIds);

        return CommonResult.success();
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public CommonResult<SysUserEntity> info(@RequestHeader(value = BaseConstant.USER_TOKEN_HEADER) UserDto userDto) {

        SysUserEntity sysUserEntity = sysUserService.getById(userDto.getId());
        return CommonResult.success(sysUserEntity);
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @PostMapping("/password")
    public CommonResult<?> password(@RequestBody PasswordForm form, @RequestHeader(BaseConstant.USER_TOKEN_HEADER) UserDto userDto) {
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");

        //更新密码
        boolean flag = sysUserService.updatePassword(userDto.getId(), form.getPassword(), form.getNewPassword());
        if (!flag) {
            return CommonResult.failed();
        }

        return CommonResult.success();
    }

}
