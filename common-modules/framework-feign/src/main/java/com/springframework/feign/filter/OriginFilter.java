package com.springframework.feign.filter;

import com.springframework.feign.annotation.OriginConfigProperties;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

/**
 * @author summer
 * 2018/7/31
 */
public class OriginFilter extends OncePerRequestFilter {
    private static final Set<String> originProperties = OriginConfigProperties.getOriginProperties();
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String X_FEIGNORIGIN_HEADER = "X-FeignOrigin";


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        //feign客户端，在调用的时候都自动增加了一个头信息：X-FeignOrigin，内容为客户端服务名
        final String feignOriginHeader = req.getHeader(X_FEIGNORIGIN_HEADER);
        //feign服务端，服务提供方通过Fitler的方式实现了读取HTTP头信息，
        // 根据自己接口定义和X-FeignOrigin中信息的关系判断是否过滤，
        // 该过滤器需要通过security.origin-filter.enabled参数开启
        if (originProperties.isEmpty() || originProperties.contains(feignOriginHeader)) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendError(SC_FORBIDDEN, "服务消费方不在提供方提供范围内");
        }
    }

    @Override
    public void destroy() {

    }
}
