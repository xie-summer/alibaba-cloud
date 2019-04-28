package com.springframework.feign.configure;

/**
 * @author summer
 */
public interface OriginServiceAspect {

    /**
     * 权限过滤
     */
    public void authenticate();
}
