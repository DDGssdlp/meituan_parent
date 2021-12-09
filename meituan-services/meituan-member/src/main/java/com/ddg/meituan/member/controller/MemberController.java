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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    public CommonResult<PageUtils> list(PageParam param) {
        PageUtils page = memberService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<MemberEntity> info(@PathVariable("id") Long id) {
        MemberEntity member = memberService.getById(id);

        return CommonResult.success(member);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody MemberEntity member) {
        memberService.save(member);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody MemberEntity member) {
        memberService.updateById(member);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids) {
        memberService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

    @PostMapping("/register")
    public CommonResult<Long> register(@RequestBody MemberRegisterVo memberRegisterVo) {
        return memberService.register(memberRegisterVo);

    }

    @PostMapping("/login")
    public CommonResult<MemberRegisterVo> login(@RequestBody MemberRegisterVo memberRegisterVo) {
        MemberRegisterVo login = memberService.login(memberRegisterVo);
        return CommonResult.success(login);

    }

    /**
     *
     * @param
     */
    @GetMapping("/getLoginUser")
    public CommonResult<UserDto> getLoginUser(@RequestHeader(value = AuthConstant.USER_TOKEN_HEADER, required =  false) UserDto userDto) {

        return CommonResult.success(userDto);
    }

    /**
     *
     * @param username
     * @return
     */
    @GetMapping("/loadByUsername")
    public UserDto loadUserByUsername(@RequestParam String username, @RequestParam String code){
        return memberService.loadUserByUsername(username, code);
    }

}
