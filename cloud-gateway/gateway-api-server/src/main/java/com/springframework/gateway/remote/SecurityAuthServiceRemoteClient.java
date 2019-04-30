package com.springframework.gateway.remote;

import com.springframework.auth.api.domain.vo.AccessTokenVO;
import com.springframework.auth.api.domain.vo.SecurityOauth2VO;
import com.springframework.domain.base.RestResult;
import com.springframework.gateway.remote.fallback.SecurityAuthServiceRemoteClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author summer
 * 2019/4/30
 */
@FeignClient(value = "auth-server", fallbackFactory = SecurityAuthServiceRemoteClientFallbackFactory.class)
public interface SecurityAuthServiceRemoteClient {
    /**
     * 授权码模式，获取 code，回调暂时不行
     *
     * @param clientId    clientId
     * @param redirectUri redirectUri
     * @return access token
     */
    @PostMapping(value = "/oauth/authorize?response_type=code", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    RestResult<String> authorize(@RequestParam(value = "client_id") String clientId,
                                 @RequestParam(value = "redirect_uri") String redirectUri);

    /**
     * 授权码模式，获取 token，回调暂时不行
     *
     * @param code         code
     * @param clientId     clientId
     * @param clientSecret clientSecret
     * @param redirectUri  redirectUri
     * @return access token
     */
    @PostMapping(value = "/oauth/token?grant_type=authorization_code", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    RestResult<SecurityOauth2VO> getAccessToken(@RequestParam(value = "code") String code,
                                                @RequestParam(value = "client_id") String clientId,
                                                @RequestParam(value = "client_secret") String clientSecret,
                                                @RequestParam(value = "redirect_uri") String redirectUri);


    /**
     * 获取 token
     *
     * @param username username
     * @param password passowrd
     * @return access token
     */
    @PostMapping(value = "/oauth/token?grant_type=password", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    RestResult<AccessTokenVO> getAccessTokenByPassword(@RequestParam(value = "username") String username,
                                                       @RequestParam(value = "password") String password);

}
