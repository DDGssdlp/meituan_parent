package com.ddg.meituan.coupon.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.coupon.entity.SkuFullReductionEntity;
import com.ddg.meituan.coupon.service.SkuFullReductionService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 商品满减信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
@RestController
@RequestMapping("coupon/skufullreduction")
public class SkuFullReductionController {
    @Autowired
    private SkuFullReductionService skuFullReductionService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<SkuFullReductionEntity>> list(PageParam param){
        PageUtils<SkuFullReductionEntity> page = skuFullReductionService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<SkuFullReductionEntity> info(@PathVariable("id") Long id){
		SkuFullReductionEntity skuFullReduction = skuFullReductionService.getById(id);

        return CommonResult.success(skuFullReduction);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody SkuFullReductionEntity skuFullReduction){
		skuFullReductionService.save(skuFullReduction);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody SkuFullReductionEntity skuFullReduction){
		skuFullReductionService.updateById(skuFullReduction);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		skuFullReductionService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
