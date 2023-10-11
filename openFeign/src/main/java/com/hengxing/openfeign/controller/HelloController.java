package com.hengxing.openfeign.controller;

import com.hengxing.openfeign.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/10/2023 23:30:52
 */
@RestController
public class HelloController {

//    @Autowired
//    HelloService helloService;
    @Autowired
    CommonService commonService;

//    @GetMapping("/login")
//    public String login(String username){
//        return helloService.login(username);
//    }

    @GetMapping("/hello")
    public String hello(){
        return commonService.hello();
    }

}
