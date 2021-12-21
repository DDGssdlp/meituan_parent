package com.ddg.meituan.member.controller;

import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.constant.AuthConstant;
import com.ddg.meituan.common.domain.UserDto;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.member.entity.MemberEntity;
import com.ddg.meituan.member.service.MemberService;
import com.ddg.meituan.member.vo.MemberRegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 会员
 *
 * @author
 * @email
 * @date 2021-01-31 16:44:02
 */
@RestController
@Api("会员")
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("会员列表")
    public PageUtils<MemberEntity> list(PageParam pageParam) {
        PageUtils<MemberEntity> page = memberService.queryPage(pageParam);

        return page;
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("会员信息")
    public CommonResult<MemberEntity> info(@PathVariable("id") Long id) {
        MemberEntity member = memberService.getById(id);

        return CommonResult.success(member);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存会员")
    public CommonResult<Object> save(@RequestBody MemberEntity member) {
        memberService.save(member);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改会员")
    public CommonResult<Object> update(@RequestBody MemberEntity member) {
        memberService.updateById(member);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除会员")
    public CommonResult<Object> delete(@RequestBody Long[] ids) {
        memberService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

    @PostMapping("/register")
    @ApiOperation("注册会员")
    public CommonResult<Long> register(@RequestBody MemberRegisterVo memberRegisterVo) {
        return memberService.register(memberRegisterVo);

    }

    @PostMapping("/login")
    @ApiOperation("会员登录")
    public CommonResult<MemberRegisterVo> login(@RequestBody MemberRegisterVo memberRegisterVo) {

        return CommonResult.success(null);

    }

    /**
     * @param
     */
    @GetMapping("/getLoginUser")
    public CommonResult<UserDto> getLoginUser(@RequestHeader(value = AuthConstant.USER_TOKEN_HEADER, required = false) UserDto userDto) {

        return CommonResult.success(userDto);
    }

    /**
     * @param username
     * @return
     */
    @GetMapping("/loadByUsername")
    public CommonResult<UserDto> loadUserByUsername(@RequestParam String username, @RequestParam String code) {
        return memberService.loadUserByUsername(username, code);
    }

}
