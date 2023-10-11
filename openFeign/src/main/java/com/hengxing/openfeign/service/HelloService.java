package com.hengxing.openfeign.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/10/2023 23:29:24
 */
//@FeignClient("provider01")
public interface HelloService {

    @GetMapping("/login")
    String login(@RequestParam("username") String username);
}
