package com.ddg.meituan.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.R;
import com.ddg.meituan.product.constant.BrandConstant;
import com.ddg.meituan.product.entity.BrandEntity;
import com.ddg.meituan.product.entity.CategoryBrandRelationEntity;
import com.ddg.meituan.product.service.CategoryBrandRelationService;
import com.ddg.meituan.product.vo.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public R relationBrandsList(@RequestParam("catId") Long catId){
        List<BrandVo> BrandVos = categoryBrandRelationService.getBrandsByCatId(catId);

        return R.ok().put("data", BrandVos);
    }

    /**
     * 获取当前品牌的所有分类列表
     */
    @GetMapping("/catelog/list")
    public R list(@RequestParam("brandId") Long brandId){
        List<CategoryBrandRelationEntity> data = categoryBrandRelationService.list(
                new QueryWrapper<CategoryBrandRelationEntity>().eq(BrandConstant.BRAND_ID, brandId)
        );

        return R.ok().put("data", data);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        categoryBrandRelationService.saveDetail(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
