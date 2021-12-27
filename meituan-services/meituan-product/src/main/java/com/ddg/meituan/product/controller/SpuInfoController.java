package com.ddg.meituan.product.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.product.entity.SpuInfoEntity;
import com.ddg.meituan.product.service.SpuInfoService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.api.CommonResult;


/**
 * spu信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {


    private final SpuInfoService spuInfoService;

    public SpuInfoController(SpuInfoService spuInfoService) {
        this.spuInfoService = spuInfoService;
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<SpuInfoEntity>> list(PageParam param){
        PageUtils<SpuInfoEntity> page = spuInfoService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<SpuInfoEntity> info(@PathVariable("id") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return CommonResult.success(spuInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody SpuInfoEntity spuInfo){
		spuInfoService.save(spuInfo);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody SpuInfoEntity spuInfo){
		spuInfoService.updateById(spuInfo);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		spuInfoService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
