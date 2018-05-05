package com.alex.entity.utils;

import lombok.Data;

/**
 *
 *
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/5/5 10:49
 */
@Data
public class UserResult<T> {

    private Integer code;

    private String msg;

    private T data;

}
