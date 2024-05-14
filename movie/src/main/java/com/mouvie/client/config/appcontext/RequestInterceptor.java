package com.mouvie.client.config.appcontext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private AppContext appContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {

        appContext.setHalJson(request.getHeader("Accept").contains("application/hal+json") || request.getHeader("Accept").contains("application/hal+xml"));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model){
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception){
    }
}
