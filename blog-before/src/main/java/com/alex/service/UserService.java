package com.alex.service;

import java.util.Map;

/**
 * 用户
 * @author zhangzhe
 */
public interface UserService {

    /**
     * 用户注册
     * @param map
     * @return
     */
    public int userRegister(Map<String,Object> map);

    /**
     * 用户激活
     * @return
     */
    public int activeUser(String userId,int state);
}
