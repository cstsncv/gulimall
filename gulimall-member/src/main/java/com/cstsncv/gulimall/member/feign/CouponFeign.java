package com.cstsncv.gulimall.member.feign;

import com.cstsncv.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("gulimall-coupon")
public interface CouponFeign {
    @RequestMapping("/coupon/coupon/member/list")
    public R membercoupons();
}
