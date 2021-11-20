package com.ddg.meituan.product.controller;

import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.product.entity.ProductInfoEntity;
import com.ddg.meituan.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/5 16:33
 * @email: wangzhijie0908@gmail.com
 */
@RestController
@RequestMapping("product/productinfo")
public class ProductInfoController {

    private final ProductInfoService ProductInfoService;

    @Autowired
    public ProductInfoController(ProductInfoService ProductInfoService) {
        this.ProductInfoService = ProductInfoService;
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public CommonResult<PageUtils> list(PageParam param){
        PageUtils page = ProductInfoService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{productId}")
    //@RequiresPermissions("product:brand:info")
    public CommonResult<ProductInfoEntity> info(@PathVariable("productId") Long brandId){
        ProductInfoEntity productInfoEntity = ProductInfoService.getById(brandId);

        return CommonResult.success(productInfoEntity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public CommonResult<Object> save(@RequestBody ProductInfoEntity productInfoEntity){
        ProductInfoService.saveProduct(productInfoEntity);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public CommonResult<Object> update(@RequestBody ProductInfoEntity productInfoEntity){
        ProductInfoService.updateById(productInfoEntity);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
        ProductInfoService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }
    /**
     *  商品上架
     * */
    @PostMapping("/up")
    //@RequiresPermissions("product:brand:delete")
    public CommonResult<Object> delete(@RequestBody Long id){
        ProductInfoService.upStateById(id);

        return CommonResult.success();
    }
}
