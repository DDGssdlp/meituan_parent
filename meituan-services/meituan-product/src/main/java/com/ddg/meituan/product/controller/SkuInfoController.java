package com.ddg.meituan.product.controller;

import java.util.Arrays;

import com.ddg.meituan.product.param.SkuInfoParam;
import com.ddg.meituan.product.vo.SkuInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.product.entity.SkuInfoEntity;
import com.ddg.meituan.product.service.SkuInfoService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.api.CommonResult;


/**
 * sku信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<SkuInfoEntity>> list(SkuInfoParam param){
        PageUtils<SkuInfoEntity> page = skuInfoService.querySkuInfoByParam(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{skuId}")
    public CommonResult<SkuInfoEntity> info(@PathVariable("skuId") Long skuId){
		SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

        return CommonResult.success(skuInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody SkuInfoEntity skuInfo){
		skuInfoService.save(skuInfo);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody SkuInfoEntity skuInfo){
		skuInfoService.updateById(skuInfo);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] skuIds){
		skuInfoService.removeByIds(Arrays.asList(skuIds));

        return CommonResult.success();
    }

}
