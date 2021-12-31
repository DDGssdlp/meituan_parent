package com.ddg.meituan.product.feign;

import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.to.SkuReduceTo;
import com.ddg.meituan.common.to.SpuBoundsTo;
import com.ddg.meituan.product.feign.fallback.CouponFeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/29 12:36
 * @email: wangzhijie0908@gmail.com
 */
@FeignClient(value = "meituan-coupon", fallback = CouponFeignServiceFallBack.class)
public interface CouponFeignService {

    @PostMapping("/coupon/spubounds/save")
    CommonResult<?> saveSpuBounds(@RequestBody SpuBoundsTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    CommonResult<?> saveSkuReduction(@RequestBody SkuReduceTo skuReduceTo);
}
