package com.hengxing.bus01.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/17/2023 22:26:49
 */
@EnableBinding(Sink.class)
public class MessageReceiver {

    @StreamListener(Sink.INPUT)
    public void handler(String message){
        System.out.println("message = " + message);
    }
}
