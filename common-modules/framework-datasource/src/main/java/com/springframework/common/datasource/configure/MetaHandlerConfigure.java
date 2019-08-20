package com.springframework.common.datasource.configure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author summer
 * 2019/1/15
 */
@Configuration
public class MetaHandlerConfigure {

    @Bean
    @ConditionalOnMissingBean
    MetaObjectHandlerConfigure metaObjectHandler() {
        return new MetaObjectHandlerConfigure();
    }
}
