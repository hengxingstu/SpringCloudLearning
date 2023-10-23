package com.hengxing.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/21/2023 20:00:24
 */
@RestController
//@RefreshScope
public class HelloController {
    @Value("${spring.datasource.username}")
    String name;

    @GetMapping("/hello")
    public String sayHello(){
        return "你好啊~~" + name;
    }
}
