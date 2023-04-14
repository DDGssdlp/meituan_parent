package com.ddg.meituan.product.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.entity.CategoryEntity;
import com.ddg.meituan.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

//import org.apache.shiro.authz.annotation.RequiresPermissions;


/**
 * 商品三级分类
 *
 * @author 
 * @email 
 * @date 2021-01-29 11:22:05
 */
@RestController
@Slf4j
@RequestMapping("product/category")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<CategoryEntity>> list(PageParam param){
        PageUtils<CategoryEntity> page = categoryService.queryPage(param);


        return CommonResult.success(page);
    }


    /**
     * 获取所有的菜单分类 以树状结构返回
     * @return
     */
    @GetMapping("/listWithTree")
    @SentinelResource("listWithTree")
    public CommonResult<List<CategoryEntity>> getListWithTree(){
        List<CategoryEntity> list =  categoryService.getListWithTree();

        return CommonResult.success(list);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    public CommonResult<CategoryEntity> info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);
		// 获取当前节点的父类路径：
        Long[] categoryPath = categoryService.findCategoryPath(catId, false);
        category.setCategoryPath(categoryPath);
        return CommonResult.success(category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody @Valid CategoryEntity category){
        boolean save = categoryService.save(category);

        return CommonResult.success(save);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody @Valid CategoryEntity category){
		categoryService.updateById(category);
        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));

        return CommonResult.success();
    }

    /**
     * 修改分类的显示状态
     * @param categoryEntity
     * @return
     */
    @PostMapping("/changeStatus")
    public CommonResult<Object> changeStatus(@RequestBody CategoryEntity categoryEntity){
        System.out.println(JSON.toJSONString(categoryEntity));
        categoryService.changeStatus(categoryEntity);
        return CommonResult.success();
    }

    /**
     * 获取当前节点下的所有子节点 分页展示
     * @param param
     * @return
     */
    @GetMapping("/getChildren")
    public CommonResult<PageUtils<CategoryEntity>> getChildren(PageParam param){
        PageUtils<CategoryEntity> page = categoryService.queryPageById(param);

        return CommonResult.success(page);
    }

    /**
     * 获取一二级分类数据展示
     * @return
     */
    @GetMapping("/getParent")
    public CommonResult<List<CategoryEntity>> getParentList(){
        List<CategoryEntity> data = categoryService.getParentList();
        return CommonResult.success(data);
    }


}
