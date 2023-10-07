package com.hengxing.hystrix.service;

import com.hengxing.common.Model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 9/28/2023 16:03:41
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand
    @CacheResult
    public User getUser(@CacheKey Integer id,String name) {
        LOGGER.info("请求了一次数据 >>> getUser:" + id);
        return restTemplate.getForObject("http://provider01/user1?id={1}", User.class, id);
    }
}
