package com.hengxing.hystrix.service;

import com.hengxing.common.Model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 向provider请求的方法，有两个要点：
     * 1. 由于provider中需要的是一个用，分割的字符串，所以需要用join方法
     * 2. getForObject中不能传List.class，因为这个方式没办法放泛型进去，我们可以先用数组，之后再用工具类转list
     * @param ids
     * @return java.util.List<com.hengxing.common.Model.User>
     * @author hengxing
     * @date 10/9/2023 22:52:45
     */
    public List<User> getUsersByIds(List<Integer> ids) {
        User[] users = restTemplate.getForObject("http://provider01/user/{1}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }
}
