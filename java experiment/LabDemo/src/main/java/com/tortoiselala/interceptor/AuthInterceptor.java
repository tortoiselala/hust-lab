package com.tortoiselala.interceptor;


import com.tortoiselala.constant.CommonConstant;
import com.tortoiselala.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理用户登录session
 */
public class AuthInterceptor implements HandlerInterceptor {
    // 不需要过滤的uri
    private static final String[] notFilter = {"index", "login.do"};

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求的uri
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        for (String s : notFilter) {
            if (requestURI.contains(s)) {
                return true;
            }
        }
        // 执行过滤
        Object user = request.getSession().getAttribute(CommonConstant.USER_CONTEXT);

        if (user == null) {
            System.out.println("user is null redirect to login ");
            response.sendRedirect(request.getContextPath() + "/static/views/login.jsp");
            /**
             * debug return true
             */
            return false;
        }
        System.out.println(user);

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
