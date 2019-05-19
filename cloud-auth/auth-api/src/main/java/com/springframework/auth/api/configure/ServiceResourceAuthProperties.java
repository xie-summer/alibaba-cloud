package com.springframework.auth.api.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author summer
 */
@Data
@ConfigurationProperties(prefix = "security.oauth.service.enable", ignoreUnknownFields = false)
public class ServiceResourceAuthProperties {

    private Boolean enable = false;

    private List<String> ignoreUrls = new ArrayList<>();
}
