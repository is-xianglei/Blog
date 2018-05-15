package com.alex.service;

/**
 * 邮件
 * @author zhangzhe
 */
public interface MailService {

    /**
     * 发送邮件
     * @param to 收件人邮箱地址
     * @param subject 标题
     * @param content 内容
     */
    public String sendSimpleMail(String to, String subject, String content);
}
