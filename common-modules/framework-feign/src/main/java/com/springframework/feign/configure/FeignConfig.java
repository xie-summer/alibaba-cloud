package com.springframework.feign.configure;

//import com.springframework.trace.constant.CatMsgConstants;

import com.netflix.loadbalancer.*;
import com.springframework.feign.configure.rule.GrayScaleRule;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author summer
 * 2018/8/1
 */
@Slf4j
public class FeignConfig implements RequestInterceptor {
    @Value("${spring.application.name}")
    private String clientServiceId;
    /**
     * 灰度路由
     */
    @Value("${spring.application.feature:}")
    private String requestFeature;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String X_FEIGNORIGIN_HEADER = "X-FeignOrigin";
    public static final String X_REQUEST_FEATURE_HEADER = "X-RequestFeature";
    @Autowired(required = false)
    private ILoadBalancer loadBalancer;
    @Autowired(required = false)
    private DiscoveryClient discoveryClient;

    private final static IRule DEFAULT_RULE = new RoundRobinRule();
    protected IRule rule = DEFAULT_RULE;

    public FeignConfig() {
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info(" 开始Feign远程调用 : " + requestTemplate.method());
        //添加 X-FeignOrigin
        if (StringUtils.hasText(clientServiceId)) {
            log.warn("this service application config not set  [spring.application.name] ");
        } else {
            requestTemplate.header(X_FEIGNORIGIN_HEADER, clientServiceId);
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (requestAttributes instanceof ServletRequestAttributes) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        if (Objects.isNull(request)) {
            log.warn(" 开始Feign远程调用 :" + requestTemplate.method() + " requestAttributes 参数为 null");
            return;
        }
        //传递请求头
        Map<String, String> headers = getHeaders(request);
        for (String headerName : headers.keySet()) {
            requestTemplate.header(headerName, getHeaders(request).get(headerName));
        }
        //支持cat 传递消息树，上下文
//        String rootId = (String) requestAttributes.getAttribute(Cat.Context.ROOT, 0);
//        String childId = (String) requestAttributes.getAttribute(Cat.Context.CHILD, 0);
//        String parentId = (String) requestAttributes.getAttribute(Cat.Context.PARENT, 0);
//        requestTemplate.header(Cat.Context.ROOT, rootId);
//        requestTemplate.header(Cat.Context.CHILD, childId);
//        requestTemplate.header(Cat.Context.PARENT, parentId);
//        requestTemplate.header(CatMsgConstants.APPLICATION_KEY, Cat.getManager().getDomain());
//        log.info(" 开始Feign远程调用 : " + requestTemplate.method() + " 消息模型 : rootId = " + rootId + " parentId = " + parentId + " childId = " + childId);
        if (StringUtils.hasText(requestFeature)) {
            requestTemplate.header(X_FEIGNORIGIN_HEADER, clientServiceId);
            if (loadBalancer == null) {
                log.warn("no load balancer");
                return;
            }
            if (loadBalancer instanceof ZoneAwareLoadBalancer) {
                rule = ((ZoneAwareLoadBalancer) loadBalancer).getRule();
                GrayScaleRule grayscaleRule = new GrayScaleRule(discoveryClient, rule, requestFeature, loadBalancer);
                ((ZoneAwareLoadBalancer) loadBalancer).setRule(grayscaleRule);
            } else if (loadBalancer instanceof DynamicServerListLoadBalancer) {
                rule = ((DynamicServerListLoadBalancer) loadBalancer).getRule();
                GrayScaleRule grayscaleRule = new GrayScaleRule(discoveryClient, rule, requestFeature, loadBalancer);
                ((DynamicServerListLoadBalancer) loadBalancer).setRule(grayscaleRule);
            } else if (loadBalancer instanceof BaseLoadBalancer) {
                rule = ((BaseLoadBalancer) loadBalancer).getRule();
                GrayScaleRule grayscaleRule = new GrayScaleRule(discoveryClient, rule, requestFeature, loadBalancer);
                ((BaseLoadBalancer) loadBalancer).setRule(grayscaleRule);
            }
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}

