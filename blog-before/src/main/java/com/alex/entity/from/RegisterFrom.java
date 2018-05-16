package com.alex.entity.from;

import com.alex.enums.UserEnum;
import lombok.Data;

/**
 * 用户注册信息实体类
 * @Author: zhangzhe
 */
@Data
public class RegisterFrom {

    /** 用户ID */
    private String id;

    /** 邮箱账号 */
    private String email;

    /** 密码 */
    private String password;

    /** 昵称 */
    private String nickname;

    /** 用户账户状态 0:未激活1：账户正常2：账户封禁3：账户不存在*/
    private Integer state = UserEnum.USER_STATE_WARING.getCode();

}
