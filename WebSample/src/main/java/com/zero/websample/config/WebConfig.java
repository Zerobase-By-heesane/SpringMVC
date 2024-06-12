package com.zero.websample.config;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<Filter> loggingFilter(){
        FilterRegistrationBean<Filter> filterregistrationBean = new FilterRegistrationBean<>();
        filterregistrationBean.setFilter(new LogFilter());
        filterregistrationBean.setOrder(1);
        // /* : 모든 요청
        // /order/* : order로 시작하는 모든 요청
        filterregistrationBean.addUrlPatterns("/order/*");

        return filterregistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "*.ico", "/error","/images/**");
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
