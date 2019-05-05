package com.springframework.gateway.configure.swagger;

import com.google.common.collect.Lists;
import com.springframework.gateway.configure.gateway.DiscoveryClientRouteDefinitionLocator;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author summer
 * 2019/4/29
 */
@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {
    public static final String API_URI = "/v2/api-docs";
    public static final String GATEWAY_SERVER_URI = "gateway-server";
    private final RouteLocator routeLocator;
    private final DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator;
    private final GatewayProperties gatewayProperties;


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //取出gateway的route
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        //结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        List<RouteDefinition> list = Lists.newArrayList();
        discoveryClientRouteDefinitionLocator.getRouteDefinitions().subscribe(list::add);
        list.stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                                predicateDefinition.getArgs().get("pattern")
                                        .replace("/**", API_URI)))));
        //过滤gateway
        if (resources.size() > 1) {
            resources.removeIf(one -> GATEWAY_SERVER_URI.equalsIgnoreCase(one.getName()));
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
