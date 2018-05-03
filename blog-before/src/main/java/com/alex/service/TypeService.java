package com.alex.service;

import com.alex.entity.Type;

import java.util.List;

/**
 * 文章类型
 * @author zhangzhe
 * @create 2018-04-30 14:51
 **/
public interface TypeService {

    /**
     * 获取文章类型列表
     * @return
     */
    public List<Type> getTypeList();
}
