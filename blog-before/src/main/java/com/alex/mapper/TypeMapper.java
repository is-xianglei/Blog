package com.alex.mapper;

import com.alex.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章类型
 * @author zhangzhe
 */
@Mapper
public interface TypeMapper {

    /**
     * 获取文章类型列表
     * @return
     */
    public List<Type> selectTpyeList();
}
