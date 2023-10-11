package com.hengxing.provider01.controller;

import com.hengxing.common.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 9/22/2023 14:24:15
 */
@Controller
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

//    @GetMapping("/hello")
//    @ResponseBody
//    public String sayHello(){
//        return "你好啊~~provider01";
//    }

    @PostMapping("/user")
    @ResponseBody
    public String user(@RequestBody User user){
        return "你好啊~~" + user.toString();
    }


    @PostMapping("/register")
    public String register(@RequestBody User user){
        LOGGER.info("注册成功，现在跳转登录页面");
        return "redirect:http://provider01/login?username=" + user.getUsername();
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(String username){
        return "欢迎你~" + username;
    }

    @PutMapping("/add1")
    @ResponseBody
    public void addUser(User user){
        LOGGER.info("添加成功" + user.getUsername());
    }

    @PutMapping("/add2")
    @ResponseBody
    public void addUser2(@RequestBody User user){
        LOGGER.info("添加成功" + user.getUsername());
    }

    @GetMapping("/user1")
    @ResponseBody
    public User getUser(Integer id){
        User hengxing = new User("hengxing", 19, "shannxi-xianyang");
        LOGGER.info("服务端返回一次数据" + hengxing);
        return hengxing;
    }

}
