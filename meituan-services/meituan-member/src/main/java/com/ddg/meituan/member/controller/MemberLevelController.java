package com.ddg.meituan.member.controller;

import java.util.Arrays;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.member.entity.MemberLevelEntity;
import com.ddg.meituan.member.service.MemberLevelService;


/**
 * 会员等级
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
@RestController
@RequestMapping("member/memberlevel")
public class MemberLevelController {
    @Autowired
    private MemberLevelService memberLevelService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("member:memberlevel:list")
    public CommonResult<PageUtils> list(PageParam param){
        PageUtils page = memberLevelService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("member:memberlevel:info")
    public CommonResult<MemberLevelEntity> info(@PathVariable("id") Long id){
		MemberLevelEntity memberLevel = memberLevelService.getById(id);

        return CommonResult.success(memberLevel);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("member:memberlevel:save")
    public CommonResult<Object> save(@RequestBody MemberLevelEntity memberLevel){
		memberLevelService.save(memberLevel);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("member:memberlevel:update")
    public CommonResult<Object> update(@RequestBody MemberLevelEntity memberLevel){
		memberLevelService.updateById(memberLevel);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("member:memberlevel:delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		memberLevelService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
