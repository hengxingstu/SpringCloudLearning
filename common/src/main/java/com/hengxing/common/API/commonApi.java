package com.hengxing.common.API;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/11/2023 00:07:02
 */
public interface commonApi {

    @GetMapping("hello")
    String hello();
}
