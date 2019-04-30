package com.springframework.gateway.remote.fallback;

import com.springframework.gateway.remote.SecurityAuthServiceRemoteClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author bailuo
 * @version 2.0
 * @Description TODO
 * @Date Created in 2018/12/11 14:18
 */
@Component(value = "securityAuthServiceRemoteClientFallbackFactory")
public class SecurityAuthServiceRemoteClientFallbackFactory implements FallbackFactory<SecurityAuthServiceRemoteClient> {
    @Override
    public SecurityAuthServiceRemoteClient create(Throwable throwable) {
        return null;
    }
}
