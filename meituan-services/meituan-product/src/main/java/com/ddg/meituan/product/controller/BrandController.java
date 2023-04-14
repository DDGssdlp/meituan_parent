package com.ddg.meituan.product.controller;

import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.entity.BrandEntity;
import com.ddg.meituan.product.service.BrandService;
import com.ddg.meituan.product.vo.BrandListVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

//import org.apache.shiro.authz.annotation.RequiresPermissions;


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

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * 列表
     */
    @GetMapping("/list")

    public CommonResult<PageUtils<BrandEntity>> list(PageParam param){
        PageUtils<BrandEntity> page = brandService.queryPage(param);

        return CommonResult.success(page);
    }

    /**
     * 列表
     */
    @GetMapping("/list/page")
    public CommonResult<PageUtils<BrandListVo>> getBrandListPage(PageParam param){
        PageUtils<BrandListVo> page = brandService.queryBrandListPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{brandId}")
    public CommonResult<BrandEntity> info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return CommonResult.success(brand);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody @Valid BrandEntity brand){
		brandService.save(brand);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody @Valid BrandEntity brand){
		brandService.updateById(brand);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
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
