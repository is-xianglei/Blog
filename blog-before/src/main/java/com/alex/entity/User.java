package com.alex.entity;

import com.alex.enums.UserEnum;
import lombok.Data;

/**
 * 用户实体类
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 15:53
 */
@Data
public class User {

    /** 用户ID */
    private String id;

    /** 用户昵称 */
    private String nickname;

    /** 用户邮箱 */
    private String email;

    /** 用户性别 */
    private String gender;

    /** 用户个性签名 */
    private String explain;

    /** 用户密码 */
    private String password;

    /** 用户头像信息 */
    private String head;

    /** 用户状态默认为:0未激活 */
    private int state = UserEnum.USER_STATE_WARING.getCode();

}
