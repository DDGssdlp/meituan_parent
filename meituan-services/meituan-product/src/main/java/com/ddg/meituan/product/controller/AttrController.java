package com.ddg.meituan.product.controller;

import java.util.Arrays;
import java.util.List;

import com.ddg.meituan.product.entity.ProductAttrValueEntity;
import com.ddg.meituan.product.service.ProductAttrValueService;
import com.ddg.meituan.product.vo.AttrRespVo;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.product.entity.AttrEntity;
import com.ddg.meituan.product.service.AttrService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.api.CommonResult;


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
    private final AttrService attrService;

    private final ProductAttrValueService productAttrValueService;

    public AttrController(AttrService attrService, ProductAttrValueService productAttrValueService) {
        this.attrService = attrService;
        this.productAttrValueService = productAttrValueService;
    }


    /**
     * 查询规格参数信息
     * @param pageParam
     * @param type
     * @return
     */
    @GetMapping("/{attrType}/list")
    public CommonResult<PageUtils<AttrRespVo>> baseAttrList(PageParam pageParam, @PathVariable("attrType")String type){

        PageUtils<AttrRespVo> page = attrService.queryAttrPageByType(pageParam,type);
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
		AttrEntity attr = attrService.getInfoById(attrId);

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

    /**
     *  获取spu规格
     */
    @GetMapping("baseAttr/spu/{spuId}")
    public CommonResult<List<ProductAttrValueEntity>> getBaseAttrOfSpu(@PathVariable String spuId){
        List<ProductAttrValueEntity> entities = productAttrValueService.getBaseAttrOfSpu(spuId);
        return CommonResult.success(entities);
    }

    /**
     * 对spu 的attr 进行修改
     * @param spuId
     * @param entities
     * @return
     */
    @PostMapping("/update/{spuId}")
    public CommonResult<?> updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities){

        productAttrValueService.updateSpuAttr(spuId,entities);

        return CommonResult.success();
    }

}
