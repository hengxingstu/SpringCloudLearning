package com.hengxing.consumer01.controller;

import com.hengxing.common.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Set;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 9/22/2023 14:29:36
 */
@Controller
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    RestTemplate restTemplate;
    @Value("${server.port}")
    int port;

    @GetMapping("/hello")
    public String sayHello() {
        return restTemplate.getForObject("http://provider01/hello?name={1}", String.class, "hengxing");
    }

    @GetMapping("/hello1")
    public void sayHello1() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://provider01/hello", String.class, "hengxing");
        String body = entity.getBody();
        HttpStatus statusCode = entity.getStatusCode();
        HttpHeaders headers = entity.getHeaders();
        Set<String> keySet = headers.keySet();//headers实现了map接口
    }

    @PostMapping("/user")
    @ResponseBody
    public String user(@RequestBody User user) {
        return restTemplate.postForObject("http://provider01/user", user, String.class);
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) {
        URI uri = restTemplate.postForLocation("http://provider01/register", user);
        LOGGER.info("正在重定向。。。");
        if (uri == null) {
            LOGGER.error("uri为空，看看是为什么");
            return "重定向失败，uri为空。";
        }
        return restTemplate.getForObject(uri, String.class);
    }

    @PutMapping("/add1")
    @ResponseBody
    public ResponseEntity add1() {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "hengxing");
        map.add("age", 19);
        map.add("address", "China");
        restTemplate.put("http://provider01/add1", map);

        restTemplate.put("http://provider01/add2", new User("hengxing", 19, "China"));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
