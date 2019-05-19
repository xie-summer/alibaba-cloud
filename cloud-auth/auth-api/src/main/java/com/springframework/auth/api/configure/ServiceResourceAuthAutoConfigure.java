package com.springframework.auth.api.configure;

import com.springframework.auth.api.filter.ServiceResourceAuthFilter;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * @author summer
 */
@Configuration
@ConditionalOnClass({ RequestMatcher.class })
@EnableConfigurationProperties(ServiceResourceAuthProperties.class)
@ConditionalOnBean(HandlerMappingIntrospector.class)
@Data
public class ServiceResourceAuthAutoConfigure {

    private ServiceResourceAuthProperties serviceResourceAuthProperties;
    private OAuth2RestTemplate oAuth2RestTemplate;
    private ApplicationContext applicationContext;

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
