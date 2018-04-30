package com.alex.controller;

import com.alex.entity.User;
import com.alex.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户登陆
 *
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 18:17
 **/
@RequestMapping(value = "/user")
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 跳转到登陆页面
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("/index")
    public ModelAndView getLogin(ModelAndView modelAndView){

        modelAndView.setViewName("login");
        return modelAndView;

    }

    /**
     * 用户登陆效验
     *
     * @param user
     * @param modelAndView
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    public ModelAndView login(User user, ModelAndView modelAndView, HttpServletRequest request){

        User login = loginService.login(user);

        modelAndView.setViewName("home");
        modelAndView.addObject("user",login);

        request.getSession().setAttribute("user",login);

        return modelAndView;
    }

}
