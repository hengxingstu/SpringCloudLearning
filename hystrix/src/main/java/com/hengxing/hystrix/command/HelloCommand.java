package com.hengxing.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 9/28/2023 11:34:37
 */
public class HelloCommand extends HystrixCommand<String> {

    RestTemplate restTemplate;

    public HelloCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        int i = 1 / 0;//异常
        return restTemplate.getForObject("http://provider01/hello", String.class);
    }

    /**
     * 这个就是服务降级的方法了
     */
    @Override
    protected String getFallback() {
        //如果是继承类的方式，就通过调用这个方法去获取异常信息
        Throwable executionException = getExecutionException();
        return "getFallback-error: " + executionException.getMessage();
    }

}
