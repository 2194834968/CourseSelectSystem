package com.lzp.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/index","/register","/Tlogin","/css/**","/swagger-ui.html","/swagger-ui/**","/webjars/**","/swagger-resources/**","/v2/**");
    }
}
