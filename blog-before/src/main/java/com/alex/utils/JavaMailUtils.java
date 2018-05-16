package com.alex.utils;

import com.alex.enums.ResultEnum;
import com.alex.exception.BlogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 发送邮件工具类
 *
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/5/16 0016 13:54
 */
@Slf4j
@Component
public class JavaMailUtils {

    private static SimpleMailMessage simpleMailMessage = new SimpleMailMessage();


    private static JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        JavaMailUtils.mailSender = mailSender;
    }

    @Value("${spring.mail.addr}")
    private static String from;

    public static void sendMail(String target,String title,String body){

        simpleMailMessage.setFrom(from);
        // 发送目标
        simpleMailMessage.setTo(target);
        // 邮件标题
        simpleMailMessage.setSubject(title);
        // 邮件正文
        simpleMailMessage.setText(body);

        try {
            mailSender.send(simpleMailMessage);

        } catch (MailException e) {
            log.info("【邮件发送失败】:{}",e.getMessage());
            throw new BlogException(ResultEnum.MAILE_SEND_ERROR.getMessage(),ResultEnum.MAILE_SEND_ERROR.getCode());
        }

    }

}
