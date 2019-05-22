package com.springframework.feign.configure.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author summer 灰度路由规则
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class GrayScaleRule extends ZoneAvoidanceRule {
    private RoundRobinRule roundRobinRule = new RoundRobinRule();

    private AtomicInteger nextServerCyclicCounter;
    private ILoadBalancer loadBalancer;
    private DiscoveryClient discoveryClient;
    private IRule iRule;

    /**
     * 灰度标记
     */
    @Value("${spring.application.feature:}")
    private String requestFeature;
//    private final String requestFeature = DynamicPropertyFactory.getInstance().getStringProperty("spring.application.feature", "").get();

    public GrayScaleRule() {
        super();
        nextServerCyclicCounter = new AtomicInteger(0);
    }

    public GrayScaleRule(DiscoveryClient discoveryClient, IRule iRule) {
        this();
        this.discoveryClient = discoveryClient;
        this.iRule = iRule;
        this.loadBalancer = this.iRule == null ? super.getLoadBalancer() : iRule.getLoadBalancer();

    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (StringUtils.hasText(requestFeature)) {
            //灰度规则
            if (lb == null) {
                log.warn("no load balancer");
                return null;
            }

            Server server = null;
            int count = 0;
            while (server == null && count++ < 10) {
                List<Server> reachableServers = lb.getReachableServers();
                List<Server> allServers = lb.getAllServers();

                int upCount = reachableServers.size();
                int serverCount = allServers.size();

                if ((upCount == 0) || (serverCount == 0)) {
                    log.warn("No up servers available from load balancer: " + lb);
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
                        + lb);
            }
            return server;
        }
        return super.choose(key);
    }

    @Override
    public Server choose(Object key) {
        return choose(loadBalancer, key);
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
        boolean filterResult = true;
        //TODO 找到对应实例,校验是否有灰度标记以及灰度版本
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
        super.setLoadBalancer(lb);
        this.loadBalancer = lb;
    }

    public void setRule(IRule subRule) {
        this.iRule = (subRule != null) ? subRule : new RoundRobinRule();
    }

    public IRule getRule() {
        return this.iRule != null ? iRule : new RoundRobinRule();
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        super.initWithNiwsConfig(iClientConfig);
    }
}
