package com.shanzhu.em.config;

import com.shanzhu.em.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 拦截器配置
 *
 * @author: ShanZhu
 * @date: 2023-11-10
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //jwt拦截器
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login", "/register", "/file/**", "/avatar/**", "/api/good/**", "/api/icon/**",
                        "/messages/**", "/api/category/**", "/api/market/**", "/api/carousel/**", "/message","/messages/**","/replay/**"
                )
                .order(0);

        WebMvcConfigurer.super.addInterceptors(registry);
    }


}
