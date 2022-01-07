package com.ddg.meituan.product.controller;

import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.product.entity.AddressEntity;
import com.ddg.meituan.product.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 地址表
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-31 12:09:54
 */
@RestController
@RequestMapping("product/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<AddressEntity>> list(PageParam param){
        PageUtils<AddressEntity> page = addressService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{addressId}")
    public CommonResult<AddressEntity> info(@PathVariable("addressId") Integer addressId){
		AddressEntity address = addressService.getById(addressId);

        return CommonResult.success(address);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody AddressEntity address){
		addressService.save(address);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody AddressEntity address){
		addressService.updateById(address);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Integer[] addressIds){
		addressService.removeByIds(Arrays.asList(addressIds));

        return CommonResult.success();
    }

}
