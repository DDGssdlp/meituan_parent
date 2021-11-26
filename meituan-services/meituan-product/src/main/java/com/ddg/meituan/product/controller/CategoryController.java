package com.ddg.meituan.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.alibaba.fastjson.JSON;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;

import com.ddg.meituan.product.entity.CategoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.product.service.CategoryService;


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
    
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("product:category:list")
    public CommonResult<PageUtils> list(PageParam param){
        PageUtils page = categoryService.queryPage(param);

        return CommonResult.success(page);
    }

    /**
     * 获取所有的菜单分类 以树状结构返回
     * @return
     */
    @GetMapping("/listWithTree")
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

        return CommonResult.success(category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody CategoryEntity category){
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
    public CommonResult<PageUtils> getChildren(PageParam param){
        PageUtils page = categoryService.queryPageById(param);

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
