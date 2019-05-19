package com.springframework.auth.api.configure;

import com.springframework.auth.api.filter.ServiceResourceVerificationAuthContextFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author summer
 */
@Configuration
@ConditionalOnProperty(prefix = "security.oauth2.service.enable", havingValue = "true",matchIfMissing=false )
public class ServiceResourceAuthAutoConfigure {


    @Bean
    public FilterRegistrationBean serviceResourceAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        ServiceResourceVerificationAuthContextFilter filter = new ServiceResourceVerificationAuthContextFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("serviceResourceAuthFilter");
        registration.setOrder(-2147482647);
        return registration;
    }

}
