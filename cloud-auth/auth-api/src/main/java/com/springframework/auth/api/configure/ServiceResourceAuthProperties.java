package com.springframework.auth.api.configure;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author summer
 */
@Data
@ConfigurationProperties(prefix = "security.oauth.service", ignoreUnknownFields = false)
public class ServiceResourceAuthProperties {

    private Boolean enable = false;

    private List<String> ignoreUrls = Lists.newArrayList("/swagger-resources/**", "/v2/api-docs", "/login", "/swagger-ui.html", "/webjars/**");
}
