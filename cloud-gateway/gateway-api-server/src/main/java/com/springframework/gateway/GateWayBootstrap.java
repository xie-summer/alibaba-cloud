package com.springframework.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author summer
 * 2018/11/22
 */
@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@EnableFeignClients
@Slf4j
@RefreshScope
public class GateWayBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(GateWayBootstrap.class, args);
        log.warn("Gateway网关服务 started success");
    }

}
