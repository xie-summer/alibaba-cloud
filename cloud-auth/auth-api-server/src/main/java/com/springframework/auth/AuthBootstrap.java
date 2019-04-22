package com.springframework.auth;

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
public class AuthBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AuthBootstrap.class, args);
        log.warn("auth 授权认证服务 started success");
    }
}
