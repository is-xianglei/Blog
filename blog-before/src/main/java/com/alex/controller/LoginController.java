package com.alex.controller;

import com.alex.entity.User;
import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.ResultVO;
import com.alex.service.ArticleService;
import com.alex.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private ArticleService articleService;

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @GetMapping("/index")
    public String getLogin(){
        return "login";

    }

    /**
     * 用户登陆效验
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    public String login(User user, HttpServletRequest request,Model model){
        User login = loginService.login(user);

        ResultVO<List<ArticleVO>> articleAll = articleService.findArticleAll(1, 10, "", "");

        model.addAttribute("ResultVO",articleAll);

        request.getSession().setAttribute("user",login);//用户信息

        // TODO 跳转登陆页面出错，页面不能获取到session中的数据

        System.err.println(login);

        return "home";
    }

}
