package com.ddg.meituan.coupon.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.coupon.entity.CouponSpuRelationEntity;
import com.ddg.meituan.coupon.service.CouponSpuRelationService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 优惠券与产品关联
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 15:07:16
 */
@RestController
@RequestMapping("coupon/couponspurelation")
public class CouponSpuRelationController {
    @Autowired
    private CouponSpuRelationService couponSpuRelationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<CouponSpuRelationEntity>> list(PageParam param){
        PageUtils<CouponSpuRelationEntity> page = couponSpuRelationService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<CouponSpuRelationEntity> info(@PathVariable("id") Long id){
		CouponSpuRelationEntity couponSpuRelation = couponSpuRelationService.getById(id);

        return CommonResult.success(couponSpuRelation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody CouponSpuRelationEntity couponSpuRelation){
		couponSpuRelationService.save(couponSpuRelation);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody CouponSpuRelationEntity couponSpuRelation){
		couponSpuRelationService.updateById(couponSpuRelation);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		couponSpuRelationService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
