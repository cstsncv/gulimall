package com.cstsncv.gulimall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cstsncv.gulimall.coupon.entity.CouponEntity;
import com.cstsncv.gulimall.coupon.service.CouponService;
import com.cstsncv.common.utils.PageUtils;
import com.cstsncv.common.utils.R;



/**
 * ÓÅ»ÝÈ¯ÐÅÏ¢
 *
 * @author csts
 * @email csts@gmail.com
 * @date 2022-02-24 14:42:51
 */
@RestController
@RequestMapping("coupon/coupon")
@Slf4j
@RefreshScope  //nacos config 动态刷新
public class CouponController {
    @Autowired
    private CouponService couponService;

    @Value("${coupon.user.name:false}")
    private String name;

    @Value("${coupon.user.age:11}")
    private Integer age;



    /**
     * 列表
     */

    @RequestMapping("/member/list")
    public R membercoupons() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满100-50");
        return R.ok().put("coupons", Arrays.asList(couponEntity));
    }

    // 测试feign
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/test")
    public R test(){
        return R.ok().put("name", name).put("age", age);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CouponEntity coupon){
		couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CouponEntity coupon){
		couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
