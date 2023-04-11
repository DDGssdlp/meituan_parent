package com.ddg.meituan.storage.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.storage.entity.PurchaseEntity;
import com.ddg.meituan.storage.service.PurchaseService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 采购信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:33:05
 */
@RestController
@RequestMapping("storage/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<PurchaseEntity>> list(PageParam param){
        PageUtils<PurchaseEntity> page = purchaseService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<PurchaseEntity> info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return CommonResult.success(purchase);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
