package com.alex.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 修改用户状态
     * @return
     */
    public int updateUserState(@Param("userId") String userId, @Param("state") int state);
}
