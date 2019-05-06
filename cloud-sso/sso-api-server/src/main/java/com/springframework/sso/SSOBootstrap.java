package com.springframework.sso;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author summer
 * 2019/1/15
 */
@Configuration
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class SSOBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SSOBootstrap.class, args);
        log.warn("SSO 服务 started success");
    }
}
