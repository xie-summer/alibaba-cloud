package com.springframework.feign.configure;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.springframework.feign.configure.rule.GrayScaleRule;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClientImportSelector;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;


/**
 * @author summer
 * 2018/12/19
 */
@Configuration
@ConditionalOnProperty(value = "feign.propagate.header.enable", matchIfMissing = true)
@Data
@Order(2147483548)
@EnableConfigurationProperties
@AutoConfigureOrder(2147483548)
@AutoConfigureAfter(value = {EnableDiscoveryClientImportSelector.class, RibbonAutoConfiguration.class})
public class FeignAutoConfig {
    public static final int DEFAULT_CONNECT_TIMEOUT = 1000;
    public static final int DEFAULT_READ_TIMEOUT = 1000;
    public static final boolean DEFAULT_GZIP_PAYLOAD = true;

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @ConditionalOnMissingBean(FeignConfig.class)
    public FeignConfig feignConfig() {
        return new FeignConfig();
    }

    @Bean("grayScaleRule")
    @Primary
    @ConditionalOnBean(value = {DiscoveryClient.class})
    @ConditionalOnProperty(value = "feign.ribbon.grayScaleRule.enable", matchIfMissing = true)
    public IRule ribbonRule(DiscoveryClient discoveryClient, @Autowired(required = false) IClientConfig config) {
        ZoneAvoidanceRule rule = new ZoneAvoidanceRule();
        GrayScaleRule grayScaleRule = new GrayScaleRule(discoveryClient, rule);
        grayScaleRule.initWithNiwsConfig(config);
        return grayScaleRule;
    }

}
