package com.ddg.meituan.member.controller;

import java.util.Arrays;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.member.entity.MemberCollectSpuEntity;
import com.ddg.meituan.member.service.MemberCollectSpuService;


/**
 * 会员收藏的商品
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
@RestController
@RequestMapping("member/membercollectspu")
public class MemberCollectSpuController {
    @Autowired
    private MemberCollectSpuService memberCollectSpuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("member:membercollectspu:list")
    public CommonResult<PageUtils> list(PageParam param){
        PageUtils page = memberCollectSpuService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("member:membercollectspu:info")
    public CommonResult<MemberCollectSpuEntity> info(@PathVariable("id") Long id){
		MemberCollectSpuEntity memberCollectSpu = memberCollectSpuService.getById(id);

        return CommonResult.success(memberCollectSpu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("member:membercollectspu:save")
    public CommonResult<Object> save(@RequestBody MemberCollectSpuEntity memberCollectSpu){
		memberCollectSpuService.save(memberCollectSpu);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("member:membercollectspu:update")
    public CommonResult<Object> update(@RequestBody MemberCollectSpuEntity memberCollectSpu){
		memberCollectSpuService.updateById(memberCollectSpu);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("member:membercollectspu:delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		memberCollectSpuService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
