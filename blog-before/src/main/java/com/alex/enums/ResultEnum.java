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

    LOGIN_EXCEPTION(004,"用户状态异常"),

    ARTICLE_SAVE_ERROR(001,"文章保存失败"),

    COMMENT_SAVE_SUCCESS(002,"评论成功"),

    COMMENT_SAVE_ERROR(003,"评论失败"),

    MAILE_SEND_ERROR(005,"邮件发送失败"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
