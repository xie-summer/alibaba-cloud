package com.springframework.feign.configure;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.springframework.feign.configure.rule.GrayScaleRule;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClientImportSelector;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.cloud.netflix.ribbon.RibbonClientName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;


/**
 * @author summer
 * 2018/12/19
 */
@Configuration
@ConditionalOnProperty(value = "feign.propagate.header.enable", matchIfMissing = true)
@Data
@Order(2147483548)
@AutoConfigureOrder(2147483548)
@AutoConfigureAfter(value = {EnableDiscoveryClientImportSelector.class})
public class FeignAutoConfig {

    @Autowired(required = false)
    private DiscoveryClient discoveryClient;
    @Autowired(required = false)
    private PropertiesFactory propertiesFactory;


    @Bean
    @ConditionalOnMissingBean(FeignConfig.class)
    public FeignConfig feignConfig() {
        return new FeignConfig();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public IRule ribbonRule() {
        return new GrayScaleRule(discoveryClient, null);
    }

}
