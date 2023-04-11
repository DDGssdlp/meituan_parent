package com.ddg.meituan.coupon.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.coupon.entity.SpuBoundsEntity;
import com.ddg.meituan.coupon.service.SpuBoundsService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 商品spu积分设置
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
@RestController
@RequestMapping("coupon/spubounds")
public class SpuBoundsController {
    @Autowired
    private SpuBoundsService spuBoundsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<SpuBoundsEntity>> list(PageParam param){
        PageUtils<SpuBoundsEntity> page = spuBoundsService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<SpuBoundsEntity> info(@PathVariable("id") Long id){
		SpuBoundsEntity spuBounds = spuBoundsService.getById(id);

        return CommonResult.success(spuBounds);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody SpuBoundsEntity spuBounds){
		spuBoundsService.save(spuBounds);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody SpuBoundsEntity spuBounds){
		spuBoundsService.updateById(spuBounds);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		spuBoundsService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
