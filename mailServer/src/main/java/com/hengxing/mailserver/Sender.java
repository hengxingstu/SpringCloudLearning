package com.hengxing.mailserver;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/14/2023 10:48:11
 */
@Component
public class Sender {

    @Autowired
    MailSender mailSender;
    @Autowired
    RestTemplate restTemplate;
    @Value("${user.username}")
    String username;
    @Value("${user.mailAddress}")
    String mailAddress;

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Scheduled(cron = "* * 12 1/1 * ? ")
    public void sendAMail(String address){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSentDate(new Date());
        message.setFrom("hengxingstu@163.com");
        message.setSubject("每日一言");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = format.format(new Date());
        message.setText(dateString + "的名言:" + getWords());
        message.setTo(StringUtils.isBlank(address)? "13149107467@163.com":address);
        mailSender.send(message);
        LOGGER.info("邮件已经发送给"+address);
    }

    public String getWords(){
        String url = "http://api.guaqb.cn/v1/onesaid/";
        String words = restTemplate.getForObject(url, String.class);
        return words;
    }
}
