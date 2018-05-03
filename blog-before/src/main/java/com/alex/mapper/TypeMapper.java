package com.alex.mapper;

import com.alex.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章类型
 * @author zhangzhe
 * @create 2018-04-30 14:51
 **/
@Mapper
public interface TypeMapper {

    /**
     * 查询所有文章类型列表
     * @return
     */
    List<Type> selectTypeList();
}
