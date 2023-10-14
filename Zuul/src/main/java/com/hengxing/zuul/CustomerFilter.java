package com.hengxing.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/12/2023 23:34:30
 */
@Component
public class CustomerFilter extends ZuulFilter {

    /**
     * 返回过滤器类型
     * - pre 前置过滤器，响应转发之前过滤
     * - post 后置过滤器，响应转发后过滤
     *
     * @date 10/12/2023 23:35:40
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器优先级，数字越大，优先级越小
     *
     * @return int
     * @author hengxing
     * @date 10/12/2023 23:39:55
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否过滤,true就过滤
     *
     * @return boolean
     * @author hengxing
     * @date 10/12/2023 23:40:15
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体的过滤方法，无需返回值，直接设置请求的状态
     *
     * @return java.lang.Object
     * @author hengxing
     * @date 10/12/2023 23:40:30
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        if (!("hengxing".equals(name) && "123".equals(pass))) {
            //直接在context中设置响应即可
            context.setSendZuulResponse(false);//不转发请求
            context.setResponseStatusCode(401);
            context.addZuulResponseHeader("content-type", "application/json;charset=utf-8");
            context.setResponseBody("验证失败，非法访问");
        }
        return null;
    }
}
