package com.springframework.gateway.domain.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springframework.gateway.domain.entity.RouteConfigDO;
import com.springframework.gateway.domain.mapper.RouteConfigMapper;
import com.springframework.gateway.domain.repository.RouteConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author summer
 * 2018/7/2
 */
@Repository("routeConfigDao")
public class RouteConfigDaoImpl extends ServiceImpl<RouteConfigMapper, RouteConfigDO> implements RouteConfigDao {

    private final RouteConfigMapper routeConfigMapper;

    @Autowired
    public RouteConfigDaoImpl(RouteConfigMapper routeConfigMapper) {
        this.routeConfigMapper = routeConfigMapper;
    }

    @Override
    public boolean saveRouteConfig(RouteConfigDO routeConfig) {
        if (routeConfig.insert()) {
            return true;
        }
        throw new RuntimeException("保存失败");
    }

    @Override
    public List<RouteConfigDO> findAll() {
        QueryWrapper<RouteConfigDO> wrapper = new QueryWrapper<>();
        RouteConfigDO routeConfig =new RouteConfigDO();
        routeConfig.selectList(wrapper);
        return routeConfig.selectList(wrapper);
    }

    @Override
    public RouteConfigDO findRouteConfig(String serviceId) {
        QueryWrapper<RouteConfigDO> wrapper = new QueryWrapper<>();
        wrapper.eq(RouteConfigDO.SERVICE_ID, serviceId);
        RouteConfigDO routeConfig =new RouteConfigDO();
        return routeConfig.selectOne(wrapper);
    }

    @Override
    public RouteConfigDO findRouteConfig(String serviceId, Boolean status) {
        QueryWrapper<RouteConfigDO> wrapper = new QueryWrapper<>();
        wrapper.eq(RouteConfigDO.SERVICE_ID, serviceId);
        wrapper.eq(RouteConfigDO.STATUS, status);
        RouteConfigDO routeConfig =new RouteConfigDO();
        return routeConfig.selectOne(wrapper);
    }

    /**
     * 根据 routeId删除配置（逻辑删除）
     *
     * @param routeId
     * @return
     */
    @Override
    public boolean deleteRouteConfigByRouteId(String routeId) {
        UpdateWrapper<RouteConfigDO> wrapper = new UpdateWrapper<>();
        wrapper.eq(RouteConfigDO.ROUTE_ID, routeId);
        RouteConfigDO entity = new RouteConfigDO();
        entity.setStatus(0);
        if (!entity.update(wrapper)) {
            throw new RuntimeException("根据 routeId删除配置失败（逻辑删除）");
        }
        return true;
    }

}
