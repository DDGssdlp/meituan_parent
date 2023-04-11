package com.ddg.meituan.storage.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.storage.entity.WareInfoEntity;
import com.ddg.meituan.storage.service.WareInfoService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 仓库信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:33:05
 */
@RestController
@RequestMapping("storage/wareinfo")
public class WareInfoController {
    @Autowired
    private WareInfoService wareInfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<WareInfoEntity>> list(PageParam param){
        PageUtils<WareInfoEntity> page = wareInfoService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<WareInfoEntity> info(@PathVariable("id") Long id){
		WareInfoEntity wareInfo = wareInfoService.getById(id);

        return CommonResult.success(wareInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody WareInfoEntity wareInfo){
		wareInfoService.save(wareInfo);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody WareInfoEntity wareInfo){
		wareInfoService.updateById(wareInfo);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		wareInfoService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
