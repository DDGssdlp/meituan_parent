package com.ddg.meituan.member.controller;

import java.util.Arrays;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.member.entity.MemberCollectSubjectEntity;
import com.ddg.meituan.member.service.MemberCollectSubjectService;


/**
 * 会员收藏的专题活动
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
@RestController
@RequestMapping("member/membercollectsubject")
public class MemberCollectSubjectController {
    @Autowired
    private MemberCollectSubjectService memberCollectSubjectService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("member:membercollectsubject:list")
    public CommonResult<PageUtils> list(PageParam param){
        PageUtils page = memberCollectSubjectService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("member:membercollectsubject:info")
    public CommonResult<MemberCollectSubjectEntity> info(@PathVariable("id") Long id){
		MemberCollectSubjectEntity memberCollectSubject = memberCollectSubjectService.getById(id);

        return CommonResult.success(memberCollectSubject);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("member:membercollectsubject:save")
    public CommonResult<Object> save(@RequestBody MemberCollectSubjectEntity memberCollectSubject){
		memberCollectSubjectService.save(memberCollectSubject);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("member:membercollectsubject:update")
    public CommonResult<Object> update(@RequestBody MemberCollectSubjectEntity memberCollectSubject){
		memberCollectSubjectService.updateById(memberCollectSubject);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("member:membercollectsubject:delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		memberCollectSubjectService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
