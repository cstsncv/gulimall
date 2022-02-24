package com.cstsncv.gulimall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cstsncv.gulimall.product.entity.BrandEntity;
import com.cstsncv.gulimall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {

//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setName("京东");
//
//        brandService.save(brandEntity);
//
//        System.out.println("保存成功........");

//        brandEntity.setBrandId(1L);
//        brandEntity.setDescript("京东商城");
//        brandService.updateById(brandEntity);
//        System.out.println("修改成功........");

        List<BrandEntity> brandEntityList = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));

        brandEntityList.forEach((item)->{
            System.out.println(item);
        });
    }

}
