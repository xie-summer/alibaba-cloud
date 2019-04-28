package com.springframework.feign.configure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author summer
 * 2018/7/31
 */
@Configuration
@ConditionalOnProperty(value = "security.origin-filter.enabled", matchIfMissing = true)
public class FeignConfiguration  {

    @Bean
    @ConditionalOnMissingBean(OriginServiceAspect.class)
    public OriginServiceAspectImpl originServiceAspect(){
        return new OriginServiceAspectImpl();
    }
}
