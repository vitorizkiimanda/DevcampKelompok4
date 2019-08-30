package com.devcamp.tokofable.config;

import com.devcamp.tokofable.component.GeneralInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private GeneralInterceptor generalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(generalInterceptor)
                .excludePathPatterns(
                        "/index.html",
                        "/app.js",
                        "/app.css",
                        "/auth/**/*",
                        "/**/register",
                        "/**/login",
                        "/pub/**/*",
                        "/test/**/*",
                        "/error/**/*", "/error")
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE", "POST", "GET", "OPTIONS")
                .allowedHeaders("token", "Content-Type", "Accept", "X-Requested-With")
                .exposedHeaders("token", "Access-Control-Allow-Origin")
                .allowCredentials(true)
                .maxAge(3600);
    }


}