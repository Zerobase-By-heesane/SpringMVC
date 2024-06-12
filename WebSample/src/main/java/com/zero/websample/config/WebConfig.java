package com.zero.websample.config;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<Filter> loggingFilter(){
        FilterRegistrationBean<Filter> filterregistrationBean = new FilterRegistrationBean<>();
        filterregistrationBean.setFilter(new LogFilter());
        filterregistrationBean.setOrder(1);
        // /* : 모든 요청
        // /order/* : order로 시작하는 모든 요청
        filterregistrationBean.addUrlPatterns("/payment");

        return filterregistrationBean;
    }

}
