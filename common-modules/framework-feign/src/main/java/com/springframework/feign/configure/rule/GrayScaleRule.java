package com.springframework.feign.configure.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import com.springframework.feign.configure.FeignConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author summer 灰度路由规则
 */
@Data
@Slf4j
public class GrayScaleRule implements IRule {
    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);
    private ILoadBalancer loadBalancer;
    private DiscoveryClient discoveryClient;
    private String requestFeature;
    private IRule iRule;

    public GrayScaleRule(DiscoveryClient discoveryClient, IRule iRule, String requestFeature, ILoadBalancer lb) {
        this.discoveryClient = discoveryClient;
        this.iRule = iRule;
        this.requestFeature = requestFeature;
        this.loadBalancer = lb;
    }

    @Override
    public Server choose(Object key) {
        if (StringUtils.hasText(requestFeature)) {
            //灰度规则
            if (loadBalancer == null) {
                log.warn("no load balancer");
                return null;
            }

            Server server = null;
            int count = 0;
            while (server == null && count++ < 10) {
                List<Server> reachableServers = loadBalancer.getReachableServers();
                List<Server> allServers = loadBalancer.getAllServers();

                int upCount = reachableServers.size();
                int serverCount = allServers.size();

                if ((upCount == 0) || (serverCount == 0)) {
                    log.warn("No up servers available from load balancer: " + loadBalancer);
                    return null;
                }

                int nextServerIndex = incrementAndGetModulo(serverCount);
                server = allServers.get(nextServerIndex);

                if (server == null) {
                    /* Transient. */
                    Thread.yield();
                    continue;
                }

                if (server.isAlive() && (server.isReadyToServe() && filterGrayScaleRule(server))) {
                    return (server);
                }

                // Next.
                server = null;
            }

            if (count >= 10) {
                log.warn("No available alive servers after 10 tries from load balancer: "
                        + loadBalancer);
            }
            return server;
        }
        return iRule.choose(key);
    }

    private boolean filterGrayScaleRule(Server server) {
        if (server == null) {
            log.warn("No servers available from load balancer: " + loadBalancer);
            return false;
        }
        if (discoveryClient == null) {
            log.warn("No  Server  DiscoveryClient ");
            return false;
        }
        Server.MetaInfo metaInfo = server.getMetaInfo();
        if (metaInfo == null) {
            log.warn("No  Server MetaInfo ");
            return false;
        }
        List<String> servicesIds = discoveryClient.getServices();
        boolean filterResult = false;
        for (String servicesId : servicesIds) {
            List<ServiceInstance> instances = discoveryClient.getInstances(servicesId);
            for (ServiceInstance instance : instances) {
                if (metaInfo.getInstanceId().equalsIgnoreCase(instance.getInstanceId())) {
                    Map<String, String> metadata = instance.getMetadata();
                    if (metadata != null && !metadata.isEmpty() && metadata.containsKey(FeignConfig.X_REQUEST_FEATURE_HEADER)) {
                        String requetFeature = metadata.getOrDefault(FeignConfig.X_REQUEST_FEATURE_HEADER, "UnDefault");
                        if (requestFeature.equalsIgnoreCase(requetFeature)) {
                            filterResult = true;
                        }
                    }
                }
            }
        }
        return filterResult;
    }

    private int incrementAndGetModulo(int modulo) {
        for (; ; ) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.loadBalancer = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return loadBalancer;
    }
}
