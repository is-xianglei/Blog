package com.alex.service;

import com.alex.entity.User;
import com.alex.entity.utils.UserResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 18:22
 **/
public interface LoginService {

    /**
     * 用户登录
     *
     * @param user
     * @param request
     * @param response
     * @return
     */
    UserResult<User> login(User user,HttpServletRequest request, HttpServletResponse response);

    /**
     * 用户修改密码
     *
     * @param user
     */
    void editPassword(User user);



}
