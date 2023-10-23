package com.hengxing.bus01.consumer;

import com.hengxing.bus01.channel.MyChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/17/2023 22:48:53
 */
@EnableBinding(MyChannel.class)
public class Consumer01 {

    @StreamListener(MyChannel.INPUT)
    public void messageReceiver(Message<String> message){
        System.out.println("message = " + message.getPayload());
    }
}
