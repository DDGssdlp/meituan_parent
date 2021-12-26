package com.ddg.meituan.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.product.constant.BrandConstant;
import com.ddg.meituan.product.entity.CategoryBrandRelationEntity;
import com.ddg.meituan.product.service.CategoryBrandRelationService;
import com.ddg.meituan.product.vo.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
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
 * @date 2021/2/5 14:37
 * @email: wangzhijie0908@gmail.com
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {


    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;


    @GetMapping("/brands/list")
    public CommonResult<List<BrandVo>> relationBrandsList(@RequestParam("catId") Long catId){
        List<BrandVo> BrandVos = categoryBrandRelationService.getBrandsByCatId(catId);

        return CommonResult.success(BrandVos);
    }

    /**
     * 获取当前品牌的所有分类列表
     */
    @GetMapping("/catelog/list")
    public CommonResult<List<CategoryBrandRelationEntity>> list(@RequestParam("brandId") Long brandId){
        List<CategoryBrandRelationEntity> data = categoryBrandRelationService.list(
                new QueryWrapper<CategoryBrandRelationEntity>().eq(BrandConstant.BRAND_ID, brandId)
        );

        return CommonResult.success(data);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils> list(@RequestParam PageParam param){
        PageUtils page = categoryBrandRelationService.queryPage(param);

        return CommonResult.success(page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<CategoryBrandRelationEntity> info(@PathVariable("id") Long id){
        CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return CommonResult.success(categoryBrandRelation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody @Valid CategoryBrandRelationEntity categoryBrandRelation){
        categoryBrandRelationService.saveDetail(categoryBrandRelation);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        categoryBrandRelationService.updateById(categoryBrandRelation);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }
}
