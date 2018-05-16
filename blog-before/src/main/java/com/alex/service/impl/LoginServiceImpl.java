package com.alex.service.impl;

import com.alex.entity.User;
import com.alex.entity.utils.UserResult;
import com.alex.enums.UserEnum;
import com.alex.mapper.LoginMapper;
import com.alex.service.LoginService;
import com.alex.utils.CookieUtils;
import com.alex.utils.JSONUtils;
import com.alex.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登陆
 *
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 18:24
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private LoginMapper loginMapper;

    /**
     * @see com.alex.service.LoginService#login
     */
    @Override
    public UserResult<User> login(User user, HttpServletRequest request, HttpServletResponse response) {

        User login = loginMapper.login(user);

        UserResult<User> userResult = new UserResult<>();

        // 用户不存在
        if (null == login){
            userResult.setCode(UserEnum.USER_ERROR.getCode());
            userResult.setMsg(UserEnum.USER_ERROR.getMessage());
            return userResult;
        }

        // 用户未激活
        if (login.getState() == UserEnum.USER_STATE_WARING.getCode()){
            userResult.setCode(UserEnum.USER_STATE_WARING.getCode());
            userResult.setMsg(UserEnum.USER_STATE_WARING.getMessage());
            return userResult;
        }

        // 用户被封禁
        if (login.getState() == UserEnum.USER_STATE_ERROR.getCode()){
            userResult.setCode(UserEnum.USER_STATE_ERROR.getCode());
            userResult.setMsg(UserEnum.USER_STATE_ERROR.getMessage());
            return userResult;
        }

        // 用户邮箱错误
        if (!login.getEmail().equals(user.getEmail())){
            userResult.setMsg(UserEnum.USER_ERROR.getMessage());
            userResult.setCode(UserEnum.USER_ERROR.getCode());
            return userResult;
        }

        // 用户密码错误
        if (!login.getPassword().equals(user.getPassword())){
            userResult.setCode(UserEnum.USER_PASSWORD_ERROR.getCode());
            userResult.setMsg(UserEnum.USER_PASSWORD_ERROR.getMessage());
            return userResult;
        }

        userResult.setMsg(UserEnum.USER_LOGIN_SUCCESS.getMessage());
        userResult.setCode(UserEnum.USER_LOGIN_SUCCESS.getCode());
        login.setPassword(null);
        userResult.setData(login);

        // 将用户信息存入Redis
        String token = "REDIS_USER_SESSION:"+ UUIDUtils.getUUID();
        //bean转json
        String strUser = JSONUtils.beanToJsonByFastjson(userResult.getData(),null);
        jedisPool.getResource().set(token, strUser);

        // 设置用户过期时间30分钟
        jedisPool.getResource().expire(token,1800);

        // 设置Cookie逻辑，cookie的有效期是关闭浏览器则失效
        CookieUtils.setCookie(request,response,"SSO-TOKEN",token);

        request.getSession().setAttribute("user",login);
        return userResult;

    }

    /**
     * @see LoginService#editPassword(com.alex.entity.User)
     */
    @Override
    public void editPassword(User user) {



    }

}
