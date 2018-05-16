package com.alex.controller;

import com.alex.entity.User;
import com.alex.entity.from.RegisterFrom;
import com.alex.entity.utils.UserResult;
import com.alex.enums.UserEnum;
import com.alex.service.LoginService;
import com.alex.service.UserService;
import com.alex.utils.CookieUtils;
import com.alex.utils.JSONUtils;
import com.alex.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisPool;
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
    private UserService userService;

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

        UserResult<User> userResult = loginService.login(user,request,response);

        // 用户登陆成功后将信息存入session
        if (userResult.getCode() == UserEnum.USER_LOGIN_SUCCESS.getCode()){

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

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value = "/registerView")
    public String register(){
        return "register";
    }

    /**
     * 用户注册
     * @param registerFrom
     * @return
     */
    @RequestMapping(value = "/register")
    public String register(RegisterFrom registerFrom, Model model,HttpServletRequest request, HttpServletResponse response){

        int i = userService.userRegister(registerFrom, request, response);

        // 用户注册成功但是账户未激活
        if (i == UserEnum.USER_STATE_WARING.getCode()){
            return "active";
        }
        return "active";

    }


    /**
     * 用户激活
     * @param userId
     * @param code
     * @return
     */
    @RequestMapping("/active")
    public String active(String userId,String code){
        //从redis中拿出验证码
        String activeCode = jedisPool.getResource().get(userId);
        if(code.equals(activeCode)){
            //验证成功修改用户状态为正常
            int result = userService.activeUser(userId, UserEnum.USER_STATE_SUCCESS.getCode());
            if(0 < result){
                return "home";
            }
        }
        //激活失败重新注册
        return "registerView";
    }


}
