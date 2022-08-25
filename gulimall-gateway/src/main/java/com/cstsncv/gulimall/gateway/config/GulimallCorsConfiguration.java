package com.cstsncv.gulimall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 注意事项:
 * 1）UrlBasedCorsConfigurationSource是import org.springframework.web.
 *    cors.reactive.UrlBasedCorsConfigurationSource;包下面的
 * 2）Spring Cloud 使用的是CorsWebFilter，而Spring boot 项目用的是 CorsFilter
 * 3）新版本的Spring boot 采用的是addAllowedOriginPattern，老版本使用的是addAllowedOrigin，
 *
 */
@Configuration
public class GulimallCorsConfiguration {
    @Bean
    public CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1. 配置跨域
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.setAllowCredentials(true);  //允许cookies

        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}
