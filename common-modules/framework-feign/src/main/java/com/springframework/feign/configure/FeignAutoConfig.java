package com.springframework.feign.configure;

import com.netflix.loadbalancer.ILoadBalancer;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author summer
 * 2018/12/19
 */
@Configuration
@ConditionalOnClass(FeignConfig.class)
@ConditionalOnProperty(value = "feign.propagate.header.enable", matchIfMissing = true)
@Data
public class FeignAutoConfig {
    @Bean
    @ConditionalOnMissingBean(FeignConfig.class)
    @ConditionalOnClass(ILoadBalancer.class)
    public FeignConfig feignConfig() {
        return new FeignConfig();
    }

}
