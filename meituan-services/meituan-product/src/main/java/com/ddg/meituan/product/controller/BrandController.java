package com.ddg.meituan.product.controller;

import java.util.Arrays;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.product.vo.BrandListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.product.entity.BrandEntity;
import com.ddg.meituan.product.service.BrandService;


/**
 * 品牌
 *
 * @author 
 * @email 
 * @date 2021-01-30 17:44:19
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public CommonResult<PageUtils> list(PageParam param){
        PageUtils page = brandService.queryPage(param);

        return CommonResult.success(page);
    }

    /**
     * 列表
     */
    @GetMapping("/list/page")
    //@RequiresPermissions("product:brand:list")
    public CommonResult<PageUtils<BrandListVo>> getBrandListPage(PageParam param){
        PageUtils<BrandListVo> page = brandService.queryBrandListPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public CommonResult<BrandEntity> info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return CommonResult.success(brand);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public CommonResult<Object> save(@RequestBody BrandEntity brand){
		brandService.save(brand);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public CommonResult<Object> update(@RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public CommonResult<Object> delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return CommonResult.success();
    }

    @PostMapping("/update/status")
    public CommonResult<Object> updateStatus(@RequestBody BrandEntity brandEntity){
        brandService.updateStatus(brandEntity);
        return CommonResult.success();
    }

}
