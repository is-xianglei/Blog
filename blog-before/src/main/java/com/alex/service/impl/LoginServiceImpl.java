package com.alex.service.impl;

import com.alex.entity.User;
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
    public User login(User user) {

        User login = loginMapper.login(user);

        // 用户不存在
        if (null == login){
            throw new BlogException(UserEnum.USER_ERROR.getMessage(),UserEnum.USER_ERROR.getCode());
        }

        // 用户未激活
        if (login.getState() == UserEnum.USER_STATE_WARING.getCode()){
            throw new BlogException(UserEnum.USER_STATE_WARING.getMessage(),UserEnum.USER_STATE_WARING.getCode());
        }

        // 用户被封禁
        if (login.getState() == UserEnum.USER_STATE_ERROR.getCode()){
            throw new BlogException(UserEnum.USER_STATE_ERROR.getMessage(),UserEnum.USER_STATE_ERROR.getCode());
        }

        // 用户邮箱错误
        if (!login.getMail().equals(user.getMail())){
            throw new BlogException(UserEnum.USER_ERROR.getMessage(),UserEnum.USER_ERROR.getCode());
        }

        // 用户密码错误
        if (!login.getPassword().equals(user.getPassword())){
            throw new BlogException(UserEnum.USER_PASSWORD_ERROR.getMessage(),UserEnum.USER_PASSWORD_ERROR.getCode());
        }

        login.setPassword(null);
        return login;

    }

    /**
     * @see LoginService#editPassword(com.alex.entity.User)
     */
    @Override
    public void editPassword(User user) {



    }

}
