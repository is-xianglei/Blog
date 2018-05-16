package com.alex.service.impl;

import com.alex.entity.Type;
import com.alex.mapper.TypeMapper;
import com.alex.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @see com.alex.service.TypeService
 * @author zhangzhe
 */
@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> typeList() {
        return typeMapper.selectTpyeList();
    }
}
