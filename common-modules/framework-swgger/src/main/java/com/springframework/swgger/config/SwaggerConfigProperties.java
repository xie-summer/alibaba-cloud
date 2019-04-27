package com.springframework.swgger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.VendorExtension;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author summer
 */@Data
@ConfigurationProperties(prefix = "spring.boot.swgger.config")
public class SwaggerConfigProperties {

    /**
     * 联系人
     */
    private Contact contact;
    /**
     * 版本
     */
    private String version = "1.0.0";
    /**
     * 标题
     */
    private String title = "Swagger API";
    /**
     * 描述
     */
    private String description = "This is to show api description";
    /**
     * url
     */
    private String termsOfServiceUrl = "";
    /**
     * 扩展
     */
    private List<VendorExtension> vendorExtensions = newArrayList();

}
