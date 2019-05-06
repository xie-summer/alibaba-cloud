package com.springframework.feign.configure;

import com.netflix.loadbalancer.ILoadBalancer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClientImportSelector;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author summer
 * 2018/12/19
 */
@Configuration
@ConditionalOnClass(FeignConfig.class)
@ConditionalOnProperty(value = "feign.propagate.header.enable", matchIfMissing = true)
@Data
@Order(2147483548)
@AutoConfigureOrder(2147483548)
@AutoConfigureAfter(value = {EnableDiscoveryClientImportSelector.class, LoadBalancerAutoConfiguration.class})
public class FeignAutoConfig {

    @Autowired(required = false)
    private DiscoveryClient discoveryClient;
    @Autowired(required = false)
    private ILoadBalancer loadBalancer;
    @Bean
    @ConditionalOnMissingBean(FeignConfig.class)
    public FeignConfig feignConfig() {
        return new FeignConfig(loadBalancer,discoveryClient);
    }

//    @Bean
//    @ConditionalOnMissingBean(ILoadBalancer.class)
//    public ILoadBalancer loadBalancer() {
//        return LoadBalancerBuilder.newBuilder().buildDynamicServerListLoadBalancer();
//    }

}
