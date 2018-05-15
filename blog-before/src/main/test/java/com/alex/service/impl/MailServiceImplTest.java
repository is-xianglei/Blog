package com.alex.service.impl;

import com.alex.service.MailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;


    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("2904878807@qq.com","test simple mail"," 激活地址：www.baidu.com");

    }

}