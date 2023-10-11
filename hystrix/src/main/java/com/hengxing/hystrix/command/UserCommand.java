package com.hengxing.hystrix.command;

import com.hengxing.common.Model.User;
import com.hengxing.hystrix.service.UserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.List;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/9/2023 22:55:56
 */
public class UserCommand extends HystrixCommand<List<User>> {

    UserService userService;
    List<Integer> ids;

    /**
     * 构建命令的构造器，把合并的id传进来，调用请求方法
     * @param userService 
	 * @param ids        
     * @return 
     * @author hengxing
     * @date 10/9/2023 23:03:28
     */
    public UserCommand(UserService userService, List<Integer> ids) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hengxing")));
        this.userService = userService;
        this.ids = ids;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.getUsersByIds(ids);
    }
}
