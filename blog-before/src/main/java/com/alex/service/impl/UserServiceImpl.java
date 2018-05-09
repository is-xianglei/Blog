package com.alex.service.impl;


import com.alex.mapper.UserMapper;
import com.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @see com.alex.service.UserService
 * @author zhangzhe
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public int userRegister(Map<String, Object> map) {
        return userMapper.addUser(map);
    }
}
