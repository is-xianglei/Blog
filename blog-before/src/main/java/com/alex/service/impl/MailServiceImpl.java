package com.alex.service.impl;

import com.alex.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @see com.alex.service.MailService
 * @author zhangzhe
 */
@Service
public class MailServiceImpl implements MailService {


    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.addr}")
    private String from;

    /**
     * 发送邮件
     * @param to 收件人邮箱地址
     * @param subject 标题
     * @param content 内容
     */
    @Override
    public String sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            System.out.println("简单邮件已经发送。");
            return "SUCCESS";
        } catch (Exception e) {
            System.out.println("发送简单邮件时发生异常！"+ e);
        }
        return "ERROR";
    }

}