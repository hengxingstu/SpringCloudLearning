package com.hengxing.hystrix.controller;

import com.hengxing.common.Model.User;
import com.hengxing.hystrix.command.UserCollapseCommand;
import com.hengxing.hystrix.service.HelloService;
import com.hengxing.hystrix.service.UserService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 9/27/2023 18:53:19
 */
@RestController
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    HelloService helloService;
    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplate;


//    @GetMapping("/hello")
//    public String sayHello() {
//        return helloService.hello();
//    }

    @GetMapping("/hello")
    public String sayHello(){
        return "你好啊~~";
    }

    @GetMapping("/errorPage")
    public String errorMethod() {
        return helloService.hello2();
    }

    @GetMapping("/hello3")
    public User getUser(Integer id){
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        LOGGER.info("第一次调用userService.getUser(id,\"hengxing\")");
        User user = userService.getUser(id,"hengxing");
        LOGGER.info("第二次调用userService.getUser(id,\"hx\")，由于cacheKey只有id，所以缓存也会生效");
        user = userService.getUser(2,"hx");
        context.close();
        return user;
    }

    @GetMapping("/hello4")
    public void getUserByIds() throws ExecutionException, InterruptedException {
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();

        UserCollapseCommand cmd1 = new UserCollapseCommand(userService, 12);
        UserCollapseCommand cmd2 = new UserCollapseCommand(userService, 13);
        UserCollapseCommand cmd3 = new UserCollapseCommand(userService, 14);
        UserCollapseCommand cmd4 = new UserCollapseCommand(userService, 15);
        Future<User> q1 = cmd1.queue();
        Future<User> q2 = cmd2.queue();
        Future<User> q3 = cmd3.queue();
        Thread.sleep(1000);//在这里等1秒，之前的请求就会先合并
        Future<User> q4 = cmd4.queue();
        User u1 = q1.get();
        User u2 = q1.get();
        User u3 = q1.get();
        User u4 = q1.get();
        System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
        System.out.println("u3 = " + u3);
        System.out.println("u4 = " + u4);
        ctx.close();
    }
}
