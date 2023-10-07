package com.hengxing.hystrix.service;

import com.hengxing.hystrix.command.HelloCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 9/28/2023 10:21:23
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 此方法可能会调用失败，我们添加 @HystrixCommand 注解之后，hytrix就会在请求失败时自动替我们路由到失败回调方法上
     *
     * @return java.lang.String
     * @author hengxing
     * @date 9/27/2023 18:57:18
     */
    @HystrixCommand(fallbackMethod = "error",ignoreExceptions = ArithmeticException.class)
    public String hello(){
        int i = 1 / 0;//异常
        return restTemplate.getForObject("http://provider01/hello", String.class);
    }


    /**
     * 此方法的名字要和fallbackMethod中配置的一样。
     *
     * @return java.lang.String
     * @author hengxing
     * @date 9/28/2023 10:24:28
     */
    public String error(Throwable t){
        return "error:" + t.getMessage();
    }

    public String hello2(){
        HelloCommand command = new HelloCommand(
                com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hengxing")),
                restTemplate);
        return command.execute();
    }
}
