package com.alex.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 用户maper接口
 * @author zhangzhe
 */
@Mapper
public interface UserMapper {

    /**
     * 用户注册
     * @param map
     * @return
     */
    public int addUser(Map<String,Object> map);
}
