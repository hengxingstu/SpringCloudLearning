package com.hengxing.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/15/2023 15:58:00
 */
@RefreshScope(proxyMode = ScopedProxyMode.DEFAULT)
@RestController
public class helloController {
    @Value("${spring.datasource.username}")
    String name;

    @GetMapping("/hello")
    public String sayHello(){
        return "你好啊~~"+ name;
    }
}
