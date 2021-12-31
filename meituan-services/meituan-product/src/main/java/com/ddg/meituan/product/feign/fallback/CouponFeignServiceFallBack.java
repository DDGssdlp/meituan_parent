package com.ddg.meituan.product.feign.fallback;

import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.to.SkuReduceTo;
import com.ddg.meituan.common.to.SpuBoundsTo;
import com.ddg.meituan.product.feign.CouponFeignService;
import org.springframework.stereotype.Component;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/29 13:57
 * @email: wangzhijie0908@gmail.com
 */
@Component
public class CouponFeignServiceFallBack implements CouponFeignService {
    @Override
    public CommonResult<?> saveSpuBounds(SpuBoundsTo spuBoundTo) {
        return null;
    }

    @Override
    public CommonResult<?> saveSkuReduction(SkuReduceTo skuReduceTo) {
        return null;
    }
}
