package com.cstsncv.gulimall.product;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  1. 整合MyBatis-Plus
 *      1), 导入依赖
 *      2), 配置:
 *          1. 配置数据源
 *              1. 导入数据库驱动
 *              2. application.yml配置数据源
 *          2. 配置MyBatis-Plus
 *              1. 使用@MapperScan注解
 *              2. sql映射文件位置
 *
 */

@MapperScan("com.cstsncv.gulimall.product.dao")
@SpringBootApplication
public class GulimallProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }
}
