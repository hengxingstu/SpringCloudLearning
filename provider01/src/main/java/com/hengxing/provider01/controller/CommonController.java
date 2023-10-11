package com.hengxing.provider01.controller;

import com.hengxing.common.API.commonApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/11/2023 00:11:07
 */
@RestController
public class CommonController implements commonApi {

    @Override
    public String hello() {
        return "欢迎你！";
    }
}
