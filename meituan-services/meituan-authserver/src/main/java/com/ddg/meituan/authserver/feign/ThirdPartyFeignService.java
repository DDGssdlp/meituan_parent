package com.ddg.meituan.authserver.feign;

import com.ddg.meituan.authserver.vo.MemberRegisterVo;
import com.ddg.meituan.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/31 16:49
 * @email: wangzhijie0908@gmail.com
 */
@FeignClient("meituan-third-party")
public interface ThirdPartyFeignService {

    @GetMapping("/thirdparty/msm/send/{phoneNum}")
    R getSendPhoneNum(@PathVariable("phoneNum") String phoneNum);


}
