package com.springframework.sso;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author summer
 * 2019/1/15
 */
@Configuration
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@Slf4j
public class SSOBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SSOBootstrap.class, args);
        log.warn("SSO 服务 started success");
    }
}
