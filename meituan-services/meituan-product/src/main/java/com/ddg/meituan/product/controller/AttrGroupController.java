package com.ddg.meituan.product.controller;

import java.util.Arrays;
import java.util.List;

import com.ddg.meituan.product.entity.AttrEntity;
import com.ddg.meituan.product.service.AttrAttrgroupRelationService;
import com.ddg.meituan.product.service.AttrService;
import com.ddg.meituan.product.vo.AttrGroupRelationVo;
import com.ddg.meituan.product.vo.AttrGroupWithAttrsVo;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.product.entity.AttrGroupEntity;
import com.ddg.meituan.product.service.AttrGroupService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 属性分组
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {

    private final AttrGroupService attrGroupService;

    private final AttrService attrService;

    private final AttrAttrgroupRelationService attrAttrgroupRelationService;

    public AttrGroupController(AttrGroupService attrGroupService, AttrService attrService, AttrAttrgroupRelationService attrAttrgroupRelationService) {
        this.attrGroupService = attrGroupService;
        this.attrService = attrService;
        this.attrAttrgroupRelationService = attrAttrgroupRelationService;
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<AttrGroupEntity>> list(PageParam param){
        PageUtils<AttrGroupEntity> page = attrGroupService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    public CommonResult<AttrGroupEntity> info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getInfoById(attrGroupId);

        return CommonResult.success(attrGroup);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return CommonResult.success();
    }

    /**
     * 删除 同时 将分组所关联的 属性页进行删除：
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeGroupAndRelationByIds(Arrays.asList(attrGroupIds));

        return CommonResult.success();
    }

    /**
     *  获取属性分组有关联的其他属性
     * @param
     * @return
     */
    @GetMapping("/relationAttr")
    public CommonResult<List<AttrEntity>> groupRelation(Long id){

        List<AttrEntity> entities = attrService.getRelationAttr(id);
        return CommonResult.success(entities);
    }
    /**
     *  获取属性分组有关联的其他属性
     * @param pageParam
     * @return
     */
    @GetMapping("/noRelationAttr")
    public CommonResult<?> groupNoRelation(PageParam pageParam){

        PageUtils<AttrEntity> page = attrService.getNoRelationAttr(pageParam);
        return CommonResult.success(page);
    }
    @PostMapping(value = "/attr/relation")
    public CommonResult<Boolean> addRelation(@RequestBody List<AttrGroupRelationVo> vos) {

        boolean b = attrAttrgroupRelationService.saveBatchByVo(vos);

        return CommonResult.success(b);

    }

    @PostMapping(value = "/attr/relation/delete")
    public CommonResult<Object> deleteRelation(@RequestBody List<AttrGroupRelationVo> vos) {

        attrService.deleteRelation(vos);

        return CommonResult.success();
    }

    @GetMapping("/withAttr/{categoryId}")
    public CommonResult<List<AttrGroupWithAttrsVo>> getAttrGroupWithAttrs(@PathVariable("categoryId") Long categoryId) {

        //1、查出当前分类下的所有属性分组
        //2、查出每个属性分组下的所有属性
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCategoryId(categoryId);


        return CommonResult.success(vos);
    }

}
