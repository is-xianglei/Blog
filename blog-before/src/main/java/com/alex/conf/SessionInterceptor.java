package com.alex.conf;

import com.alex.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 19:35
 **/
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = (User) request.getSession().getAttribute("user");

        if (null == user){
            response.sendRedirect("/user/index");
        }

        request.getSession().setAttribute("user",user);

        return true;
    }

}
