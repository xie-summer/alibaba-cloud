package com.springframework.feign.configure;

import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author summer
 * 2018/12/19
 */
@Configuration
@ConditionalOnClass(FeignConfig.class)
@ConditionalOnProperty(value = "feign.propagate.header.enable", matchIfMissing = false)
@Data
public class FeignAutoConfig {
    @Bean
    @ConditionalOnMissingBean(FeignConfig.class)
    @ConditionalOnClass(ILoadBalancer.class)
    public FeignConfig feignConfig() {
        return new FeignConfig();
    }


    @Bean
    @ConditionalOnMissingBean
    public ILoadBalancer loadBalancer() {
        return new DynamicServerListLoadBalancer<>();
    }


}
