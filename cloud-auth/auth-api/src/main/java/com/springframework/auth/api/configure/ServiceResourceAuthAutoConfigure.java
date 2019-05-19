package com.springframework.auth.api.configure;

import com.springframework.auth.api.filter.ServiceResourceAuthFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author summer
 */
@Configuration
@ConditionalOnProperty(value  = "security.oauth2.service.enable", havingValue = "true",matchIfMissing=false )
public class ServiceResourceAuthAutoConfigure {


    @Bean
    public FilterRegistrationBean serviceResourceAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        ServiceResourceAuthFilter filter = new ServiceResourceAuthFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("serviceResourceAuthFilter");
        registration.setOrder(-2147482647);
        return registration;
    }

}
