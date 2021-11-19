package com.ddg.meituan.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.alibaba.fastjson.JSON;
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
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取所有的菜单分类 以树状结构返回
     * @return
     */
    @GetMapping("/listWithTree")
    public R getListWithTree(){
        List<CategoryEntity> list =  categoryService.getListWithTree();

        return R.ok().put("data", list);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    //@RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

    /**
     * 修改分类的显示状态
     * @param categoryEntity
     * @return
     */
    @PostMapping("/changeStatus")
    public R changeStatus(@RequestBody CategoryEntity categoryEntity){
        System.out.println(JSON.toJSONString(categoryEntity));
        categoryService.changeStatus(categoryEntity);
        return R.ok();
    }

    /**
     * 获取当前节点下的所有子节点 分页展示
     * @param params
     * @return
     */
    @GetMapping("/getChildren")
    public R getChildren(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPageById(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取一二级分类数据展示
     * @return
     */
    @GetMapping("/getParent")
    public R getParentList(){
        List<CategoryEntity> data = categoryService.getParentList();
        return R.ok().put("data", data);
    }


}
