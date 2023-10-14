package com.hengxing.mailserver.controller;

import com.hengxing.mailserver.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/14/2023 11:04:13
 */
@RestController
public class MailController {

    @Autowired
    Sender sender;
    @Value("${user.mailAddress}")
    String mailAddress;

    @GetMapping("/hello")
    public String sayHello(){
        return "你好啊~~";
    }

    @GetMapping("/mail")
    public String sendMail(){
        sender.sendAMail("");
        return "已调用接口，请等待接收邮件";
    }

    @GetMapping("/to")
    public String sendTo(String address){
        sender.sendAMail(address);
        return "已经向"+ address + "发送一言。";
    }
}
