package com.alex.service;

import com.alex.entity.User;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 18:22
 **/
public interface LoginService {

    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 用户修改密码
     *
     * @param user
     */
    void editPassword(User user);



}
