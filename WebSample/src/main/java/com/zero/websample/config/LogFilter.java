package com.zero.websample.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain
    ) throws IOException, ServletException {
        // 외부 -> Filter (-> Controller ->) filter -> 외부
        // (-> Controller ->) : Filter Chain
        // 1~10까지 Thread가 있음
        log.info("Hello Filter : "+Thread.currentThread().getName());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("Bye Filter : "+Thread.currentThread().getName());
    }
}
