package com.alex.entity.from;

import lombok.Data;

/**
 * 用户注册信息实体类
 * @Author: zhangzhe
 */
@Data
public class RegisterFrom {

    /** 邮箱账号 */
    private String email;

    /** 密码 */
    private String password;

    /** 昵称 */
    private String nickname;

    /** 个性签名 */
    private String explain;

}
