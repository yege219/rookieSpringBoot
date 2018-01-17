package com.rookie.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by rookie on 2018/1/17.
 * 发送emial邮件的service
 */
@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        // message.setTo("zhangyechen@yxqiche.com");
        message.setTo("zhangyechen219@163.com");
        message.setSubject("邮件主题是");
        message.setText("邮件正文是");
        mailSender.send(message);
        log.info("邮件已发送");
    }
}
