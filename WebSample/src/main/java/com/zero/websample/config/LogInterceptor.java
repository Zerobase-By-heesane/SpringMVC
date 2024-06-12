package com.zero.websample.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    // handling 전
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle Log Interceptor : " + Thread.currentThread());
        log.info("preHandle handle : " + handler);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    // handling 한 후
    // ModelAndView : Controller에서 Model에 담아서 보낸 데이터
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle Log Interceptor : " + Thread.currentThread());
        log.info("postHandle handle : " + handler);

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // 완료 후
    // 요청이 성공하든 실패하든 Exception이 발생하든 무조건 실행
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion Log Interceptor : " + Thread.currentThread());
        log.info("afterCompletion handle : " + handler);

        if(ex != null){
            log.error("afterCompletion error : " + ex.getMessage());
        }

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
