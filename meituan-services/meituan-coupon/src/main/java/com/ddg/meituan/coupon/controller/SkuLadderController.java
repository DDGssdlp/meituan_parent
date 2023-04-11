package com.ddg.meituan.coupon.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.coupon.entity.SkuLadderEntity;
import com.ddg.meituan.coupon.service.SkuLadderService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 商品阶梯价格
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
@RestController
@RequestMapping("coupon/skuladder")
public class SkuLadderController {
    @Autowired
    private SkuLadderService skuLadderService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<SkuLadderEntity>> list(PageParam param){
        PageUtils<SkuLadderEntity> page = skuLadderService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<SkuLadderEntity> info(@PathVariable("id") Long id){
		SkuLadderEntity skuLadder = skuLadderService.getById(id);

        return CommonResult.success(skuLadder);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody SkuLadderEntity skuLadder){
		skuLadderService.save(skuLadder);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody SkuLadderEntity skuLadder){
		skuLadderService.updateById(skuLadder);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		skuLadderService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
