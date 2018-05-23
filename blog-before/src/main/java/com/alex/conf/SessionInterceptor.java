package com.alex.conf;

import com.alex.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 19:35
 **/
@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Jedis resource = jedisPool.getResource();

        String cookieValue = CookieUtils.getCookieValue(request, "SSO-TOKEN");

        log.info("【SSO-TOKEN】:{}",cookieValue);

        if(null == cookieValue){
            response.sendRedirect("/user/index");
            return false;
        }

        String userSesion = resource.get(cookieValue);

        if (null == userSesion || "".equals(userSesion)) {
            response.sendRedirect("/user/index");
            return false;
        }

        // 更新生命周期
        jedisPool.getResource().expire("REDIS_USER_SESSION:"+cookieValue,300);
        return true;

    }

}
