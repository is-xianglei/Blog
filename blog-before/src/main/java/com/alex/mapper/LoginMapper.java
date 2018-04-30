package com.alex.mapper;

import com.alex.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 18:25
 **/
@Mapper
public interface LoginMapper {

    User login(@Param("user")User user);

}
