package com.cstsncv.gulimall.product;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
 *  2. 逻辑删除
 *      1), 配置全局逻辑删除规则 application.yml(可省略)
 *      2), 配置逻辑删除的组件Bean(可省略)
 *      3), 实体类字段上加上@TableLogic注解
 */

@EnableDiscoveryClient
@MapperScan("com.cstsncv.gulimall.product.dao")
@SpringBootApplication
public class GulimallProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }
}
