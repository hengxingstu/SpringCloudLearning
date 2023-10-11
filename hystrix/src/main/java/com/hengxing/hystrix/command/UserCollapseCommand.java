package com.hengxing.hystrix.command;

import com.hengxing.common.Model.User;
import com.hengxing.hystrix.service.UserService;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 这个才是真正请求合并的类，我们将来的请求，都是通过构建这个对象进行发送的
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/9/2023 23:03:27
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>,User,Integer> {
    UserService userService;
    Integer id;

    /**
     * withTimerDelayInMilliseconds(200) 是说请求到达后会等待200毫秒，如果在此期间有其他请求就会将当前的请求合并，然后再等200毫秒
     * @param userService
	 * @param id
     * @return
     * @author hengxing
     * @date 10/9/2023 23:25:11
     */
    public UserCollapseCommand(UserService userService, Integer id) {
        super(HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("hengxing"))
                .andCollapserPropertiesDefaults(
                        HystrixCollapserProperties.Setter()
                        .withTimerDelayInMilliseconds(200)));
        this.userService = userService;
        this.id = id;
    }

    /**
     * 拿到请求的参数，然后返回给对应的方法
     * @return java.lang.Integer
     * @author hengxing
     * @date 10/9/2023 23:10:35
     */
    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
     * 合并请求的方法
     * @param collapsedRequests 已经合并的请求
     * @return com.netflix.hystrix.HystrixCommand<java.util.List<com.hengxing.common.Model.User>>
     * @author hengxing
     * @date 10/9/2023 23:11:13
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Integer>> collapsedRequests) {
        List<Integer> ids = collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList());
        return new UserCommand(userService,ids);
    }

    /**
     * 将结果映射到请求对象上，此处就是简单的按照顺序把请求和结果拼接到了一起
     * @param batchResponse 服务器返回的请求结果
	 * @param collapsedRequests 合并的请求
     * @return void
     * @author hengxing
     * @date 10/9/2023 23:19:45
     */
    @Override
    protected void mapResponseToRequests(List<User> batchResponse, Collection<CollapsedRequest<User, Integer>> collapsedRequests) {
        int index = 0;
        for (CollapsedRequest<User, Integer> request : collapsedRequests) {
            request.setResponse(batchResponse.get(index++));
        }
    }
}
