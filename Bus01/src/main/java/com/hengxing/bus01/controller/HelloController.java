package com.hengxing.bus01.controller;

import com.hengxing.bus01.channel.MyChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/17/2023 23:00:29
 */
@RestController
public class HelloController {

    @Autowired
    MyChannel myChannel;

    @GetMapping("/hello")
    public String sayHello(){
        Message<String> message = new Message<String>() {
            @Override
            public String getPayload() {
                return "123";
            }

            @Override
            public MessageHeaders getHeaders() {
                HashMap<String, Object> headers = new HashMap<>();
                headers.put(MessageHeaders.CONTENT_TYPE, "application/json");
                headers.put(MessageHeaders.TIMESTAMP, new Date());
                return new MessageHeaders(headers);
            }
        };
        for (int i = 0; i < 10; i++) {
            boolean sent = myChannel.output().send(message);
        }
        return "已发送消息";
    }
}
