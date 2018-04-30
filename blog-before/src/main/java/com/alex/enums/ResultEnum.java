package com.alex.enums;

import lombok.Getter;

/**
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 16:27
 */
@Getter
public enum  ResultEnum {

    SUCCESS(200, "成功"),

    PARAM_ERROR(500, "参数不正确"),

    LOGIN_FAIL(505, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(404, "登出成功"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
