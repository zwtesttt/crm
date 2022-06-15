package com.zw.controller.lanjie;



import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.zw.gongong.changliang.ReturnObject.SESSION_USER;

public class UrlLanjie implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean flag=false;
//        获取session对象
        HttpSession session=request.getSession();
//        如果session域中没有user对象就重定向到首页
        if (session.getAttribute(SESSION_USER)!=null){
            flag=true;
        }else {
            response.sendRedirect(request.getContextPath());
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
