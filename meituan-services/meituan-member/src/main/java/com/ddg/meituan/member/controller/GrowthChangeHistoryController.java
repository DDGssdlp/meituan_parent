package com.ddg.meituan.member.controller;

import java.util.Arrays;


import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.member.entity.GrowthChangeHistoryEntity;
import com.ddg.meituan.member.service.GrowthChangeHistoryService;


/**
 * 成长值变化历史记录
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
@RestController
@RequestMapping("member/growthchangehistory")
public class GrowthChangeHistoryController {
    @Autowired
    private GrowthChangeHistoryService growthChangeHistoryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("member:growthchangehistory:list")
    public CommonResult<PageUtils> list(PageParam param){
        PageUtils page = growthChangeHistoryService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("member:growthchangehistory:info")
    public CommonResult<GrowthChangeHistoryEntity> info(@PathVariable("id") Long id){
		GrowthChangeHistoryEntity growthChangeHistory = growthChangeHistoryService.getById(id);

        return CommonResult.success(growthChangeHistory);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("member:growthchangehistory:save")
    public CommonResult<Object> save(@RequestBody GrowthChangeHistoryEntity growthChangeHistory){
		growthChangeHistoryService.save(growthChangeHistory);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("member:growthchangehistory:update")
    public CommonResult<Object> update(@RequestBody GrowthChangeHistoryEntity growthChangeHistory){
		growthChangeHistoryService.updateById(growthChangeHistory);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("member:growthchangehistory:delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		growthChangeHistoryService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
