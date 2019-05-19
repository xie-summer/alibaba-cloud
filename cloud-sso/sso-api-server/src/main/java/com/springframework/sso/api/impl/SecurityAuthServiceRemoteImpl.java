package com.springframework.sso.api.impl;

import com.springframework.auth.api.domain.vo.AccessTokenVO;
import com.springframework.auth.api.domain.vo.SecurityOauth2VO;
import com.springframework.domain.base.RestResult;
import com.springframework.sso.SecurityAuthServiceRemote;
import com.springframework.sso.remote.SecurityAuthServiceRemoteClient;
import com.springframework.sso.remote.UserServiceClient;
import com.springframework.user.api.domain.vo.UserVO;
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
    public RestResult<String> authorize(@RequestParam(value = "response_type") String responseType,
                                        @RequestParam(value = "client_id") String clientId,
                                        @RequestParam(value = "client_secret") String clientSecret,
                                        @RequestParam(value = "redirect_uri") String redirectUri) {
        String authorize = securityAuthServiceRemoteClient.authorize(clientId, clientSecret, redirectUri);

        return new RestResult<>(authorize);
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
                                                       @RequestParam(value = "grant_type") String grantType,
                                                       @RequestParam(value = "client_secret") String clientSecret,
                                                       @RequestParam(value = "redirect_uri") String redirectUri) {
        SecurityOauth2VO accessToken = securityAuthServiceRemoteClient.getAccessToken(code, clientId, clientSecret, redirectUri);
        return new RestResult<>(accessToken);
    }


    /**
     * 获取 token
     *
     * @param username username
     * @param password passowrd
     * @return access token
     */
    @Override
    public RestResult<AccessTokenVO> getAccessTokenByPassword(
                                                              @RequestParam(value = "client_id") String clientId,
                                                              @RequestParam(value = "client_secret") String clientSecret,
                                                              @RequestParam(value = "username") String username,
                                                              @RequestParam(value = "password") String password) {
        AccessTokenVO accessToken = securityAuthServiceRemoteClient.getAccessTokenByPassword(username, clientId, clientSecret, password);
        return new RestResult<>(accessToken);
    }
}
