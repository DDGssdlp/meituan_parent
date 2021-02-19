package com.ddg.meituan.product.controller;

import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.R;
import com.ddg.meituan.product.entity.BrandEntity;
import com.ddg.meituan.product.entity.ProductInfoEntity;
import com.ddg.meituan.product.service.BrandService;
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
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ProductInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{productId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("productId") Long brandId){
        ProductInfoEntity productInfoEntity = ProductInfoService.getById(brandId);

        return R.ok().put("brand", productInfoEntity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@RequestBody ProductInfoEntity productInfoEntity){
        ProductInfoService.saveProduct(productInfoEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@RequestBody ProductInfoEntity productInfoEntity){
        ProductInfoService.updateById(productInfoEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] ids){
        ProductInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     *  商品上架
     * */
    @PostMapping("/up")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long id){
        ProductInfoService.upStateById(id);

        return R.ok();
    }
}
