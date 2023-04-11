package com.ddg.meituan.order.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ddg.meituan.order.entity.OrderItemEntity;
import com.ddg.meituan.order.service.OrderItemService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.api.CommonResult;


/**
 * 订单项信息
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:36:27
 */
@RestController
@RequestMapping("order/orderitem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<OrderItemEntity>> list(PageParam param){
        PageUtils<OrderItemEntity> page = orderItemService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<OrderItemEntity> info(@PathVariable("id") Long id){
		OrderItemEntity orderItem = orderItemService.getById(id);

        return CommonResult.success(orderItem);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody OrderItemEntity orderItem){
		orderItemService.save(orderItem);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody OrderItemEntity orderItem){
		orderItemService.updateById(orderItem);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		orderItemService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
