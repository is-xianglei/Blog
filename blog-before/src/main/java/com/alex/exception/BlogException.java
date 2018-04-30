package com.alex.exception;

import com.alex.enums.ResultEnum;

/**
 * 全局异常处理
 *
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 16:24
 */
public class BlogException extends RuntimeException {

    private int code;

    public BlogException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BlogException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
