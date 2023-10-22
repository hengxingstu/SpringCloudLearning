package com.hengxing.bus01.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/17/2023 22:45:10
 */
public interface MyChannel {
//    消息通道的名称
    String INPUT = "input01";
    String OUTPUT = "output01";

    //绑定对应的队列名称
    @Input(INPUT)
    MessageChannel input();
    @Output(OUTPUT)
    MessageChannel output();
}
