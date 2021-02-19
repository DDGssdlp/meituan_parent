package com.ddg.meituan.member.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.alibaba.fastjson.JSON;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.member.constant.MemberConstant;
import com.ddg.meituan.member.vo.MemberRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.member.entity.MemberEntity;
import com.ddg.meituan.member.service.MemberService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.R;

import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;


/**
 * 会员
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("/register")
    public R register(@RequestBody MemberRegisterVo memberRegisterVo) throws MeituanSysException {
        return memberService.register(memberRegisterVo);

    }
    @PostMapping("/login")
    public R login(@RequestBody MemberRegisterVo memberRegisterVo, HttpSession session) throws MeituanSysException {
        return memberService.login(memberRegisterVo, session);

    }

    @GetMapping("/getLoginUser/{phoneNum}")
    public R getLoginUser(@PathVariable String phoneNum){
        String memberRegisterVoStr = (String) redisTemplate.opsForHash()
                .get(MemberConstant.REDIS_CACHE_LOGIN_USER_KEY, phoneNum);
        MemberRegisterVo memberRegisterVo = JSON.parseObject(memberRegisterVoStr, MemberRegisterVo.class);
        return R.ok().put("loginUser", memberRegisterVo);
    }

}
