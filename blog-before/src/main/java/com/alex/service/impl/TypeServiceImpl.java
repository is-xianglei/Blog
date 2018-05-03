package com.alex.service.impl;

import com.alex.entity.Type;
import com.alex.mapper.TypeMapper;
import com.alex.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章类型
 * @author zhangzhe
 * @create 2018-04-30 14:51
 **/
@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeMapper typeMapper;
    /**
     * 获取文章类型列表
     * @return
     */
    @Override
    public List<Type> getTypeList() {
        return typeMapper.selectTypeList();
    }
}
