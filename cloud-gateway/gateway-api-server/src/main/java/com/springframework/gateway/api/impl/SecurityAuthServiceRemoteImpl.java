package com.springframework.gateway.api.impl;

import com.springframework.auth.api.domain.vo.AccessTokenVO;
import com.springframework.auth.api.domain.vo.SecurityOauth2VO;
import com.springframework.domain.base.RestResult;
import com.springframework.gateway.api.SecurityAuthServiceRemote;
import com.springframework.gateway.remote.SecurityAuthServiceRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author summer
 * 2019/4/30
 */
@RestController
public class SecurityAuthServiceRemoteImpl implements SecurityAuthServiceRemote {

    private final SecurityAuthServiceRemoteClient securityAuthServiceRemoteClient;

    @Autowired
    public SecurityAuthServiceRemoteImpl(SecurityAuthServiceRemoteClient securityAuthServiceRemoteClient) {
        this.securityAuthServiceRemoteClient = securityAuthServiceRemoteClient;
    }

    /**
     * 授权码模式，获取 code，回调暂时不行
     *
     * @param clientId    clientId
     * @param redirectUri redirectUri
     * @return access token
     */
    @Override
    public RestResult<String> authorize(@RequestParam(value = "client_id") String clientId,
                                        @RequestParam(value = "redirect_uri") String redirectUri) {
        return securityAuthServiceRemoteClient.authorize(clientId, redirectUri);
    }

    /**
     * 授权码模式，获取 token，回调暂时不行
     *
     * @param code         code
     * @param clientId     clientId
     * @param clientSecret clientSecret
     * @param redirectUri  redirectUri
     * @return access token
     */
    @Override
    public RestResult<SecurityOauth2VO> getAccessToken(@RequestParam(value = "code") String code,
                                                       @RequestParam(value = "client_id") String clientId,
                                                       @RequestParam(value = "client_secret") String clientSecret,
                                                       @RequestParam(value = "redirect_uri") String redirectUri) {
        return securityAuthServiceRemoteClient.getAccessToken(code, clientId, clientSecret, redirectUri);
    }


    /**
     * 获取 token
     *
     * @param username username
     * @param password passowrd
     * @return access token
     */
    @Override
    public RestResult<AccessTokenVO> getAccessTokenByPassword(@RequestParam(value = "username") String username,
                                                              @RequestParam(value = "password") String password) {
        return securityAuthServiceRemoteClient.getAccessTokenByPassword(username, password);
    }
}
