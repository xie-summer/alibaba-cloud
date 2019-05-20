package com.springframework.auth.api.configure;

import com.springframework.auth.api.filter.ServiceResourceAuthFilter;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author summer
 */
@Configuration
@EnableConfigurationProperties(ServiceResourceAuthProperties.class)
@Data
public class ServiceResourceAuthAutoConfigure {


    @Bean
    @ConditionalOnBean({RestTemplate.class})
    public FilterRegistrationBean serviceResourceAuthFilter(LoadBalancerClient loadBalancerClient, RestTemplate restTemplate, ServiceResourceAuthProperties serviceResourceAuthProperties, ApplicationContext applicationContext) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        ServiceResourceAuthFilter filter = new ServiceResourceAuthFilter();
        filter.setServiceResourceAuthProperties(serviceResourceAuthProperties);
        filter.setRestTemplate(restTemplate);
        filter.setApplicationContext(applicationContext);
        filter.setLoadBalancerClient(loadBalancerClient);
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("serviceResourceAuthFilter");
        registration.setOrder(-2147482647);
        return registration;
    }


}
