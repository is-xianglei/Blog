package com.alex.controller;

import com.alex.entity.User;
import com.alex.entity.from.RegisterFrom;
import com.alex.entity.utils.UserResult;
import com.alex.enums.UserEnum;
import com.alex.service.LoginService;
import com.alex.service.MailService;
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
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private MailService mailService;

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
            //bean转json
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
        Map<String,Object> registerMap = new HashMap<>();
        String userId = UUIDUtils.getUUID();
        registerMap.put("id",userId);
        registerMap.put("nickname",registerFrom.getNickname());
        registerMap.put("email",registerFrom.getEmail());
        registerMap.put("explain",registerFrom.getExplain());
        registerMap.put("password",registerFrom.getPassword());
        //暂时写死
        registerMap.put("gender","男");
        registerMap.put("head","https://picsum.photos/200/300?image=0");
        //默认设置用户状态为未激活0
        registerMap.put("state",UserEnum.USER_STATE_WARING.getCode());
        int num = userService.userRegister(registerMap);
        //注册成功，发送邮件提示用户去激活
        if(0 < num){
            //生成6位随机数作为激活码存入redis
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String code = sdf.format(new Date()).substring(8,14);
            jedisPool.getResource().set(userId,code);
            //收件人，标题，内容
            String userEmail = registerFrom.getEmail();
            String title = "激活链接";
            String activeUrl = "此链接有效期为一天，请在一天内激活，点击激活：http://127.0.0.1:9888/user/active?userId="+userId+"&"+"code="+code;
            mailService.sendSimpleMail(userEmail,title,activeUrl);
            //设置redis过期时间为一天
            jedisPool.getResource().expire(userId,86400);
            // 设置Cookie逻辑，cookie的有效期是关闭浏览器则失效
            CookieUtils.setCookie(request,response,"SSO-TOKEN",userId);
            //进入验证页面提示用户去邮箱激活
            return "active";
        }
        return "registerView";

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
