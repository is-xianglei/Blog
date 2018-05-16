package com.alex.service;

import com.alex.entity.from.RegisterFrom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户
 * @author zhangzhe
 */
public interface UserService {

    /**
     * 用户注册
     * @param registerFrom
     * @return
     */
    public int userRegister(RegisterFrom registerFrom, HttpServletRequest request, HttpServletResponse response);

    /**
     * 用户激活
     * @return
     */
    public int activeUser(String userId,int state);

}
