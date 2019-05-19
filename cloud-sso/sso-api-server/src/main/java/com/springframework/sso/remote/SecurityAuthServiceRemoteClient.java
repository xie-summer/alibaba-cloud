package com.springframework.sso.remote;

import com.springframework.auth.api.domain.vo.AccessTokenVO;
import com.springframework.auth.api.domain.vo.SecurityOauth2VO;
import com.springframework.domain.base.RestResult;
import com.springframework.sso.remote.fallback.SecurityAuthServiceRemoteClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  * <p>
 *  * 授权码模式只能在本服务中调用:
 *  * 授权码模式：1、授权：http://localhost:8086/oauth/authorize?response_type=code&client_id=client_3&redirect_uri=https://www.baidu.com
 *  * 2、获取token：http://127.0.0.1:8086/oauth/token?grant_type=authorization_code&code=123456&client_id=client_3&client_secret=secret&redirect_uri=http://baidu.com
 *  * <p>
 *  * 客户端模式：http://127.0.0.1:8086/oauth/token?grant_type=client_credentials&client_id=client_1&client_secret=secret
 *  * 密码模式： http://127.0.0.1:8086/oauth/token?username=admin&password=123456&grant_type=password&client_id=client_2&client_secret=secret
 *  * <p>
 *  * 1. /oauth/authorize：授权端点。
 *  * 2. /oauth/token：获取token。
 *  * 3. /oauth/confirm_access：用户确认授权提交端点。
 *  * 4. /oauth/error：授权服务错误信息端点。
 *  * 5. /oauth/check_token：用于资源服务访问的令牌解析端点。
 *  * 6. /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话。
 *  * 7. /oauth/logout: 退出
 * @author summer
 * 2019/4/30
 */
@FeignClient(value = "auth-server", fallbackFactory = SecurityAuthServiceRemoteClientFallbackFactory.class)
public interface SecurityAuthServiceRemoteClient {
    /**
     * 授权码模式，获取 code，回调暂时不行
     *
     * @param clientId    clientId
     * @param clientSecret secret
     * @param redirectUri redirectUri
     * @return access token
     */
    @RequestMapping(value = "/oauth/authorize?response_type=code", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    String authorize(@RequestParam(value = "client_id") String clientId,
                                 @RequestParam(value = "client_secret") String clientSecret,
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
    @RequestMapping(value = "/oauth/token?grant_type=authorization_code", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    SecurityOauth2VO getAccessToken(@RequestParam(value = "code") String code,
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
    @RequestMapping(value = "/oauth/token?grant_type=password&response_type=code", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    AccessTokenVO getAccessTokenByPassword(@RequestParam(value = "username") String username,
                                                       @RequestParam(value = "client_id") String clientId,
                                                       @RequestParam(value = "client_secret") String clientSecret,
                                                       @RequestParam(value = "password") String password);

}
