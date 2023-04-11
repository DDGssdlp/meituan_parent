package com.ddg.meituan.coupon.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.coupon.entity.SeckillSkuRelationEntity;
import com.ddg.meituan.coupon.service.SeckillSkuRelationService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 秒杀活动商品关联
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
@RestController
@RequestMapping("coupon/seckillskurelation")
public class SeckillSkuRelationController {
    @Autowired
    private SeckillSkuRelationService seckillSkuRelationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<SeckillSkuRelationEntity>> list(PageParam param){
        PageUtils<SeckillSkuRelationEntity> page = seckillSkuRelationService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<SeckillSkuRelationEntity> info(@PathVariable("id") Long id){
		SeckillSkuRelationEntity seckillSkuRelation = seckillSkuRelationService.getById(id);

        return CommonResult.success(seckillSkuRelation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody SeckillSkuRelationEntity seckillSkuRelation){
		seckillSkuRelationService.save(seckillSkuRelation);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody SeckillSkuRelationEntity seckillSkuRelation){
		seckillSkuRelationService.updateById(seckillSkuRelation);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		seckillSkuRelationService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
