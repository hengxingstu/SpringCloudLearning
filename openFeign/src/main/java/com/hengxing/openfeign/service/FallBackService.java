package com.hengxing.openfeign.service;

import org.springframework.stereotype.Component;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/11/2023 20:53:35
 */
@Component
//@RequestMapping("/errorMethod")
public class FallBackService implements CommonService{
    @Override
    public String hello() {

        return "hello，我是服务降级方法~~";
    }
}
