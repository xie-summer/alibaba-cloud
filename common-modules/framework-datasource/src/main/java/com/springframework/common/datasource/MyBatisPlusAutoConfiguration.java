/**
 *
 */
package com.springframework.common.datasource;

import com.springframework.common.datasource.actuator.DataSourceHealthIndicator;
import com.springframework.common.datasource.configure.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * @author summer
 *
 */
@Slf4j
@Configuration
@ConditionalOnBean(DataSource.class)
public class MyBatisPlusAutoConfiguration {


    @Bean
    @ConditionalOnBean(Executor.class)
    public DataSourceHealthIndicator dataSourceHealthIndicator() {
        return new DataSourceHealthIndicator();
    }

    @Bean
    @Primary
    @ConditionalOnClass(PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
