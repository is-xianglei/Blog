package com.alex.controller;

import com.alex.entity.User;
import com.alex.entity.utils.UserResult;
import com.alex.enums.UserEnum;
import com.alex.service.ArticleService;
import com.alex.service.LoginService;
import com.alex.utils.CookieUtils;
import com.alex.utils.JSONUtils;
import com.alex.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    @Autowired
    private JedisPool jedisPool;

    /**
     * 跳转到登陆页面
     * @return
     */
    @GetMapping("/index")
    public String getLogin(){
        return "login";

    }

    /**
     * 用户登陆效验
     * @param user
     * @param
     * @return
     */
    @PostMapping(value = "/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response){

        UserResult<User> userResult = loginService.login(user);

        // 用户登陆成功后将信息存入session
        if (userResult.getCode() == UserEnum.USER_LOGIN_SUCCESS.getCode()){

            // 将用户信息存入Redis
            String token = "REDIS_USER_SESSION:"+UUIDUtils.getUUID();
            String strUser = JSONUtils.beanToJsonByFastjson(userResult.getData(),null);
            jedisPool.getResource().set(token, strUser);

            // 设置用户过期时间30分钟
            jedisPool.getResource().expire(token,30);

            // 设置Cookie逻辑，cookie的有效期是关闭浏览器则失效
            CookieUtils.setCookie(request,response,"SSO-TOKEN",token);

            request.getSession().setAttribute("user",userResult.getData());

            return "redirect:/";
        }

        request.getSession().setAttribute("user",userResult);
        return "login";

    }

    /**
     * 用户退出
     * @param session
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");

        // 清空用户信息
//        jedisPool.getResource().del()

        return "login";
    }



}
