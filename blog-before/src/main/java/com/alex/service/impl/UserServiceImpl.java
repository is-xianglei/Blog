package com.alex.service.impl;

import com.alex.entity.from.RegisterFrom;
import com.alex.enums.ResultEnum;
import com.alex.enums.UserEnum;
import com.alex.exception.BlogException;
import com.alex.mapper.UserMapper;
import com.alex.service.UserService;
import com.alex.utils.CookieUtils;
import com.alex.utils.JavaMailUtils;
import com.alex.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * @see com.alex.service.UserService
 * @author zhangzhe
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public int userRegister(RegisterFrom registerFrom, HttpServletRequest request, HttpServletResponse response) {

        // 设置用户ID
        registerFrom.setId(UUIDUtils.getUUID());

        try {
            int i = userMapper.addUser(registerFrom);
            String value = String.valueOf(new Random().nextInt(1000000));
            jedisPool.getResource().set(UserEnum.USER_REGISTER_ID+":"+registerFrom.getId(),value);
            //收件人，标题，内容
            String userEmail = registerFrom.getEmail();
            String title = "激活链接";
            String activeUrl = "此链接有效期为一天，请在一天内激活,http://127.0.0.1:9888/user/active?userId="+registerFrom.getId()+"&"+"code="+value+"点击激活";
            // 发送邮件
            JavaMailUtils.sendMail(userEmail,title,activeUrl);
            //设置redis过期时间为一天
            jedisPool.getResource().expire(registerFrom.getId(),86400);

            return UserEnum.USER_STATE_WARING.getCode();
        } catch (Exception e) {
            log.info("【用户注册失败】:{}",e.getMessage());
            throw new BlogException("【异常信息】:{}"+e.getMessage(), ResultEnum.LOGIN_EXCEPTION.getCode());
        }
    }


    @Override
    public int activeUser(String userId, int state) {

        try {
            // 获取邮箱激活码
            String emailCode = jedisPool.getResource().get(UserEnum.USER_REGISTER_ID + ":" + userId);

            // 如果为null则已经过期需要重新发送邮件激活
            if (null ==emailCode) {

            }

            // 如果邮箱的验证码与Redis中相同则通过验证，修改用户状态
            if (String.valueOf(state).equals(emailCode)){
                int x = userMapper.updateUserState(userId, state);
            }

        } catch (Exception e) {
            log.info("【用户激活失败】:用户ID:{}",userId,"【异常信息】:{}",e.getMessage());
            throw new BlogException("【异常信息】:{}"+e.getMessage(), ResultEnum.LOGIN_EXCEPTION.getCode());
        }
        return 1;
    }
}
