package com.ddg.meituan.storage.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.storage.entity.WareOrderTaskDetailEntity;
import com.ddg.meituan.storage.service.WareOrderTaskDetailService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 库存工作单
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:33:05
 */
@RestController
@RequestMapping("storage/wareordertaskdetail")
public class WareOrderTaskDetailController {
    @Autowired
    private WareOrderTaskDetailService wareOrderTaskDetailService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<WareOrderTaskDetailEntity>> list(PageParam param){
        PageUtils<WareOrderTaskDetailEntity> page = wareOrderTaskDetailService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<WareOrderTaskDetailEntity> info(@PathVariable("id") Long id){
		WareOrderTaskDetailEntity wareOrderTaskDetail = wareOrderTaskDetailService.getById(id);

        return CommonResult.success(wareOrderTaskDetail);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail){
		wareOrderTaskDetailService.save(wareOrderTaskDetail);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail){
		wareOrderTaskDetailService.updateById(wareOrderTaskDetail);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		wareOrderTaskDetailService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
