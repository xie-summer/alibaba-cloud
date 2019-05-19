package com.springframework.sso;

import com.springframework.auth.api.domain.vo.AccessTokenVO;
import com.springframework.auth.api.domain.vo.SecurityOauth2VO;
import com.springframework.domain.base.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author summer
 * 2019/4/29
 */
@Api(value = "授权接口", tags = "授权接口")
@RequestMapping("/v1/oauth")
public interface SecurityAuthServiceRemote {


    /**
     * 授权码模式，获取 code，回调暂时不行
     *
     * @param clientId    clientId
     * @param redirectUri redirectUri
     * @return access token
     */
    @GetMapping(value = "/authorize")
    @ApiOperation(value = "授权码模式，获取 code，回调暂时不行", notes = "授权码模式，获取 code，回调暂时不行")
    RestResult<String> authorize(String responseType, String clientId, String clientSecret,String redirectUri);

    /**
     * 授权码模式，获取 token，回调暂时不行
     *
     * @param code         code
     * @param clientId     clientId
     * @param clientSecret clientSecret
     * @param redirectUri  redirectUri
     * @return access token
     */
    @GetMapping(value = "/token")
    @ApiOperation(value = "授权码模式，获取 token，回调暂时不行", notes = "授权码模式，获取 token，回调暂时不行")
    RestResult<SecurityOauth2VO> getAccessToken(String code,
                                                String clientId,
                                                String grantType,
                                                String clientSecret,
                                                String redirectUri);


    /**
     * 获取 token
     *
     * @param clientId    客户端id
     * @param clientSecret 客户端 secret
     * @param username username
     * @param password passowrd
     * @return access token
     */
    @GetMapping(value = "/password/token")
    @ApiOperation(value = "密码模式 获取token", notes = "密码模式 获取token")
    RestResult<AccessTokenVO> getAccessTokenByPassword(String clientId,
                                                       String clientSecret, String username, String password);

}
