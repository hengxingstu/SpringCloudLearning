package com.hengxing.provider01.controller;

import com.hengxing.common.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/7/2023 19:00:44
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 这个是合并的请求方法
     * @param ids 合并的请求列表
     * @return java.util.List<com.hengxing.common.Model.User>
     * @author hengxing
     * @date 10/9/2023 22:35:37
     */
    @GetMapping("/{ids}")
    @ResponseBody
    public List<User> getUsers(@PathVariable String ids){
        System.out.println("getUsers>>>>>>" + ids);
        String[] strings = ids.split(",");
        ArrayList<User> users = new ArrayList<>();
        for (String id: strings){
            User user = new User(id, 20, "unkown");
            users.add(user);
        }
        if (users.isEmpty()) return Collections.emptyList();
        return users;
    }
}
