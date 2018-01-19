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

    /**
     * 该对象集成了配置文件中邮件发送的配置信息
     */
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送邮件
     * @param to 发送给哪个邮箱
     */
    public void sendMail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        //message.setTo("zhangyechen219@163.com");
        message.setSubject("邮件主题是");
        message.setText("邮件正文是");
        mailSender.send(message);
        log.info("邮件已发送给:" + to);
    }
}
