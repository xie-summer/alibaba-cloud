package com.springframework.gateway.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.route.*;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author summer
 * 2018/7/4
 */
@RestController

@Api(value = "网关接口",tags = "网关接口")
@RequestMapping("/gateway")
@Slf4j
public class GatewayRouteController implements ApplicationEventPublisherAware {

    private RouteDefinitionLocator routeDefinitionLocator;
    private List<GlobalFilter> globalFilters;
    private List<GatewayFilterFactory> gatewayFilters;
    private RouteDefinitionWriter routeDefinitionWriter;
    private RouteLocator routeLocator;
    private ApplicationEventPublisher publisher;

    public GatewayRouteController(RouteDefinitionLocator routeDefinitionLocator, List<GlobalFilter> globalFilters, @Autowired(required = false) List<GatewayFilterFactory> gatewayFilters, RouteDefinitionWriter routeDefinitionWriter, RouteLocator routeLocator, ApplicationEventPublisher publisher) {
        this.routeDefinitionLocator = routeDefinitionLocator;
        this.globalFilters = globalFilters;
        this.gatewayFilters = gatewayFilters;
        this.routeDefinitionWriter = routeDefinitionWriter;
        this.routeLocator = routeLocator;
        this.publisher = publisher;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    // TODO: Add uncommited or new but not active routes endpoint

    @PostMapping("/refresh")
    @ApiOperation(value = "刷新路由",notes = "刷新路由")
    public Mono<String> refresh() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return Mono.just("刷新成功");
    }

    @GetMapping("/globalfilters")
    @ApiOperation(value = "查找所有global路由过滤器",notes = "查找所有global路由过滤器")
    public Mono<HashMap<String, Object>> globalfilters() {
        return getNamesToOrders(this.globalFilters);
    }

    @GetMapping("/routefilters")
    @ApiOperation(value = "查找所有路由过滤器",notes = "查找所有路由过滤器")
    public Mono<HashMap<String, Object>> routefilers() {
        return getNamesToOrders(this.gatewayFilters);
    }

    private <T> Mono<HashMap<String, Object>> getNamesToOrders(List<T> list) {
        return Flux.fromIterable(list).reduce(new HashMap<>(), this::putItem);
    }

    private HashMap<String, Object> putItem(HashMap<String, Object> map, Object o) {
        Integer order = null;
        if (o instanceof Ordered) {
            order = ((Ordered) o).getOrder();
        }
        //filters.put(o.getClass().getName(), orders);
        map.put(o.toString(), order);
        return map;
    }

    // TODO: Flush out routes without a definition
    @GetMapping("/routes")
    @ApiOperation(value = "获取路由列表",notes = "获取路由列表")
    public Mono<List<Map<String, Object>>> routes() {
        Mono<Map<String, RouteDefinition>> routeDefs = this.routeDefinitionLocator.getRouteDefinitions()
                .collectMap(RouteDefinition::getId);
        Mono<List<Route>> routes = this.routeLocator.getRoutes().collectList();
        return Mono.zip(routeDefs, routes).map(tuple -> {
            Map<String, RouteDefinition> defs = tuple.getT1();
            List<Route> routeList = tuple.getT2();
            List<Map<String, Object>> allRoutes = new ArrayList<>();

            routeList.forEach(route -> {
                HashMap<String, Object> r = new HashMap<>();
                r.put("route_id", route.getId());
                r.put("orders", route.getOrder());

                if (defs.containsKey(route.getId())) {
                    r.put("route_definition", defs.get(route.getId()));
                } else {
                    HashMap<String, Object> obj = new HashMap<>();

                    obj.put("predicate", route.getPredicate().toString());

                    if (!route.getFilters().isEmpty()) {
                        ArrayList<String> filters = new ArrayList<>();
                        for (GatewayFilter filter : route.getFilters()) {
                            filters.add(filter.toString());
                        }

                        obj.put("filters", filters);
                    }

                    if (!obj.isEmpty()) {
                        r.put("route_object", obj);
                    }
                }
                allRoutes.add(r);
            });

            return allRoutes;
        });
    }

    /*
    http POST :8080/admin/gateway/routes/apiaddreqhead uri=http://httpbin.org:80 predicates:='["Host=**.apiaddrequestheader.org", "Path=/headers"]' filters:='["AddRequestHeader=X-Request-ApiFoo, ApiBar"]'
    */
    @PostMapping("/routes/{id}")
    @ApiOperation(value = "根据serviceID添加路由",notes = "根据serviceID添加路由")
    @SuppressWarnings("unchecked")
    public Mono<ResponseEntity<Void>> save(@PathVariable String id, @RequestBody Mono<RouteDefinition> route) {
        return this.routeDefinitionWriter.save(route.map(r -> {
            r.setId(id);
            if (log.isDebugEnabled()) {
                log.debug("Saving route: " + route);
            }
            return r;
        })).then(Mono.defer(() ->
                Mono.just(ResponseEntity.created(URI.create("/routes/" + id)).build())
        ));
    }

    @DeleteMapping("/routes/{id}")
    @ApiOperation(value = "根据路由ID删除指定路由",notes = "根据路由ID删除指定路由")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return this.routeDefinitionWriter.delete(Mono.just(id))
                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/routes/{id}")
    @ApiOperation(value = "根据路由ID查找路由详情",notes = "根据路由ID查找路由详情")
    public Mono<ResponseEntity<RouteDefinition>> route(@PathVariable String id) {
        //TODO: missing RouteLocator
        return this.routeDefinitionLocator.getRouteDefinitions()
                .filter(route -> route.getId().equals(id))
                .singleOrEmpty()
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/routes/{id}/combinedfilters")
    @ApiOperation(value = "根据路由ID指定路由过滤器",notes = "根据路由ID指定路由过滤器")
    public Mono<HashMap<String, Object>> combinedfilters(@PathVariable String id) {
        //TODO: missing global filters
        return this.routeLocator.getRoutes()
                .filter(route -> route.getId().equals(id))
                .reduce(new HashMap<>(), this::putItem);
    }
}