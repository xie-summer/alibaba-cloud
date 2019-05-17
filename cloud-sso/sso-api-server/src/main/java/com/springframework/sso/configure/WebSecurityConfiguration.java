package com.springframework.sso.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author summer
 */
@Configuration
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Override
    public void configure(WebSecurity web) {
        LOGGER.info("不拦截路径设置！");
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html**",
                "/v1/oauth/**");
    }
}
