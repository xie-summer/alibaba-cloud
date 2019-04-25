package com.springframework.gateway.service;

import com.springframework.cache.GenericCacheManager;
import com.springframework.gateway.domain.po.RouteConfigDO;

/**
 * @author summer
 * 2018/11/28
 */
public interface RouteConfigCacheService extends GenericCacheManager {
    /**
     * 保存缓存
     *
     * @param routeConfig
     */
    void saveRouteConfigCache(RouteConfigDO routeConfig);

    /** 缓存查询
     * @param serviceId
     * @return
     */
    RouteConfigDO findRouteConfigCache(String serviceId);

    /** 缓存过期
     * @param routeId
     * @return
     */
    void expireRouteConfigCache(String routeId);
}
