package com.springframework.auth.security;


import com.springframework.auth.security.handler.CustomAccessDeineHandler;
import com.springframework.auth.security.handler.CustomAuthenticationEntryPoint;
import com.springframework.auth.security.handler.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author summer
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices tokenServices;
    @Value("${security.oauth2.client.resource-ids:*}")
    private String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId).stateless(true).tokenServices(tokenServices);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(new OAuthRequestedMatcher())
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/login", "/oauth/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated()
                .and()
                //认证失败的业务处理
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint())
                .accessDeniedHandler(customAccessDeineHandler())
                .and()
                .logout()
                .logoutUrl("/oauth/logout")
                //退出成功的业务处理
                .logoutSuccessHandler(customLogoutSuccessHandler());
    }

    @Bean
    @ConditionalOnMissingBean
    public LogoutSuccessHandler customLogoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean
    public AccessDeniedHandler customAccessDeineHandler() {
        return new CustomAccessDeineHandler();
    }

}
