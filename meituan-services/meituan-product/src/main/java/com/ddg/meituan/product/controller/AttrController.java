package com.ddg.meituan.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.ddg.meituan.product.vo.AttrRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.product.entity.AttrEntity;
import com.ddg.meituan.product.service.AttrService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.api.CommonResult;


/**
 * 商品属性
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;


    /**
     * 查询规格参数信息
     * @param pageParam
     * @param type
     * @return
     */
    @GetMapping("/{attrType}/list")
    public CommonResult<?> baseAttrList(PageParam pageParam, @PathVariable("attrType")String type){

        PageUtils<AttrRespVo> page = attrService.queryBaseAttrPage(pageParam,type);
        return CommonResult.success(page);
    }


    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<AttrEntity>> list(PageParam param){
        PageUtils<AttrEntity> page = attrService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrId}")
    public CommonResult<AttrEntity> info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);

        return CommonResult.success(attr);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody AttrEntity attr){
		attrService.save(attr);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return CommonResult.success();
    }

}
