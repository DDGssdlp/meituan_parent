package com.ddg.meituan.order.controller;

import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.order.entity.OrderEntity;
import com.ddg.meituan.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 订单
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:36:27
 */
@RestController
@RequestMapping("order/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * 列表
     */
    @GetMapping("/list")
    public CommonResult<PageUtils<OrderEntity>> list(PageParam param){
        PageUtils<OrderEntity> page = orderService.queryPage(param);

        return CommonResult.success(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public CommonResult<OrderEntity> info(@PathVariable("id") Long id){
		OrderEntity order = orderService.getById(id);

        return CommonResult.success(order);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult<Object> save(@RequestBody OrderEntity order){
		orderService.save(order);

        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult<Object> update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestBody Long[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return CommonResult.success();
    }

}
