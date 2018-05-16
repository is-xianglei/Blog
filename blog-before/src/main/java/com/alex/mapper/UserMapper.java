package com.alex.mapper;

import com.alex.entity.from.RegisterFrom;
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
     * @param registerFrom
     * @return
     */
    public int addUser(@Param("registerFrom") RegisterFrom registerFrom);

    /**
     * 修改用户状态
     * @return
     */
    public int updateUserState(@Param("userId") String userId, @Param("state") int state);
}
