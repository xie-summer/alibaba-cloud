package com.springframework.gateway.service;

import com.springframework.gateway.domain.dto.RouteConfigDTO;
import com.springframework.gateway.domain.po.RouteConfigDO;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * 路由配置服务接口
 *
 * @author summer
 * 2018/7/2
 */
public interface RouteConfigService {
    /**
     * 保存路由配置
     *
     * @param routeConfig
     * @return
     */
    boolean saveRouteConfig(RouteConfigDO routeConfig);

    /**
     * 保存路由配置
     *
     * @param routeConfig
     */
    boolean saveRouteConfig(RouteConfigDTO routeConfig);

    /**
     * 查询所有路由配置
     *
     * @return
     */
    List<RouteDefinition> listRouteDefinition();


    /**
     * 查询路由
     *
     * @param serviceId 服务id
     * @return
     */
    RouteConfigDTO findRouteConfig(String serviceId);

    /**
     * 根据routeId 删除配置（逻辑删除）
     *
     * @param id
     * @return
     */
    boolean deleteRouteConfigByRouteId(String id);

    /**
     * @param routeConfig
     * @return
     */
    public RouteConfigDTO covertToRouteConfigDTO(RouteConfigDO routeConfig);

    /**
     * @return 查询所有路由配置
     */
    List<RouteConfigDO> listAllRouteConfig();

    /**
     * @param serviceId 根据serviceId查询
     * @return
     */
    RouteConfigDO getRouteConfigByServiceId(String serviceId);

    /**
     * @param id
     * @return
     */
    RouteConfigDO getRouteConfigById(Long id);

    /**
     * @param id
     */
    void delRouteConfigById(Long id);
}
