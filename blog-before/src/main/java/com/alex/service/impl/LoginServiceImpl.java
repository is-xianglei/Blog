package com.alex.service.impl;

import com.alex.entity.User;
import com.alex.entity.utils.UserResult;
import com.alex.entity.vo.ResultVO;
import com.alex.enums.UserEnum;
import com.alex.exception.BlogException;
import com.alex.mapper.LoginMapper;
import com.alex.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户登陆
 *
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 18:24
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    /**
     * @see LoginService#login(com.alex.entity.User)
     */
    @Override
    public UserResult<User> login(User user) {

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
        if (!login.getMail().equals(user.getMail())){
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
        return userResult;

    }

    /**
     * @see LoginService#editPassword(com.alex.entity.User)
     */
    @Override
    public void editPassword(User user) {



    }

}
