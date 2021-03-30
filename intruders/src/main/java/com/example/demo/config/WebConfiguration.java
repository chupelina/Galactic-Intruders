package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final LogoutInterceptor logoutInterceptor;

    public WebConfiguration(LogoutInterceptor logoutInterceptor) {
        this.logoutInterceptor = logoutInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logoutInterceptor);
    }
}
