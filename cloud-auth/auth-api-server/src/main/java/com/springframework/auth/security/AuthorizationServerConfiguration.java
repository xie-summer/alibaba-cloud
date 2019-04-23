package com.springframework.auth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author summer
 * @Description 授权认证服务配置类
 * <p>
 * 授权码模式只能在本服务中调用:
 * 授权码模式：1、授权：http://localhost:8086/oauth/authorize?response_type=code&client_id=client_3&redirect_uri=https://www.baidu.com
 * 2、获取token：http://127.0.0.1:8086/oauth/token?grant_type=authorization_code&code=123456&client_id=client_3&client_secret=secret&redirect_uri=http://baidu.com
 * <p>
 * 客户端模式：http://127.0.0.1:8086/oauth/token?grant_type=client_credentials&client_id=client_1&client_secret=secret
 * 密码模式： http://127.0.0.1:8086/oauth/token?username=admin&password=123456&grant_type=password&client_id=client_2&client_secret=secret
 * <p>
 * 1. /oauth/authorize：授权端点。
 * 2. /oauth/token：获取token。
 * 3. /oauth/confirm_access：用户确认授权提交端点。
 * 4. /oauth/error：授权服务错误信息端点。
 * 5. /oauth/check_token：用于资源服务访问的令牌解析端点。
 * 6. /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话。
 * 7. /oauth/logout: 退出
 * <p>
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);

    /**
     * 客户端1 用来标识客户的Id
     */
    @Value("${security.oauth2.client.credentials-client-id}")
    private String credentialsClientId;
    /**
     * 客户端2
     */
    @Value("${security.oauth2.client.password-client-id}")
    private String passwordClientId;
    /**
     * 客户端3
     */
    @Value("${security.oauth2.client.code-client-id}")
    private String codeClientId;
    /**
     * secret客户端安全码
     */
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    /**
     * 密码模式授权模式
     */
    @Value("${security.oauth2.client.grant-type-password}")
    private String grantTypePassword;
    /**
     * 授权码模式  授权码模式使用到了回调地址，是最为复杂的方式，通常网站中经常出现的微博，qq第三方登录，都会采用这个形式。
     */
    @Value("${security.oauth2.client.grant-type-authorization-code}")
    private String authorizationCode;

    @Value("${security.oauth2.client.refresh-token}")
    private String refreshToken;
    /**
     * 简化授权模式
     */
    private static final String IMPLICIT = "implicit";
    /**
     * 客户端模式
     */
    @Value("${security.oauth2.client.grant-type-client-credentials}")
    private String grantType;
    @Value("${security.oauth2.client.scope-read}")
    private String scopeRead;
    @Value("${security.oauth2.client.scope-write}")
    private String scopeWrite;
    private static final String TRUST = "trust";

    @Value("${security.oauth2.client.access-token-validity-seconds}")
    private int accessTokenValiditySeconds;

    @Value("${security.oauth2.client.refresh-token-validity-seconds}")
    private int refreshTokenValiditySeconds;
    /**
     * 指定哪些资源是需要授权验证的
     */
    @Value("${security.oauth2.client.resource-ids}")
    private String resourceIds;


    /**
     * 认证方式
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnection;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DataSource dataSource;


    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        LOGGER.info("===============配置授权服务器开始...=========");
        // 用 BCrypt 对密码编码
        String secret = new BCryptPasswordEncoder().encode(clientSecret);
//        configurer.jdbc(dataSource)
        //配置3个个客户端,一个用于password认证、一个用于client认证、一个用于authorization_code认证
        // 使用in-memory存储
        configurer.inMemory()
                .withClient(credentialsClientId)
                //client_id用来标识客户的Id  客户端1
                .resourceIds(resourceIds)
                //允许授权类型   客户端授权模式
                .authorizedGrantTypes(grantType, refreshToken)
                //允许授权范围
                .scopes(scopeRead, scopeWrite)
                //客户端可以使用的权限
                .authorities("oauth2")
                //secret客户端安全码
                .secret(secret)
                //token 时间秒
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                //刷新token 时间 秒
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)

                .and()
                //client_id用来标识客户的Id  客户端 2
                .withClient(passwordClientId)
                .resourceIds(resourceIds)
                //允许授权类型  密码授权模式
                .authorizedGrantTypes(grantTypePassword, refreshToken)
                //允许授权范围
                .scopes(scopeRead, scopeWrite)
                //客户端可以使用的权限
                .authorities("oauth2")
                //secret客户端安全码
                .secret(secret)
                //token 时间秒
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                //刷新token 时间 秒
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)

                .and()
                //client_id用来标识客户的Id  客户端 3
                .withClient(codeClientId)
                .resourceIds(resourceIds)
                //允许授权类型  授权码模式
                .authorizedGrantTypes(authorizationCode, refreshToken)
                //允许授权范围
                .scopes(scopeRead, scopeWrite)
                //客户端可以使用的权限
                .authorities("oauth2")
                //secret客户端安全码
                .secret(secret)
                .redirectUris("https://www.baidu.com")
                //token 时间秒
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                //刷新token 时间 秒
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds);

        LOGGER.info("===============配置授权服务器完成=========");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore())
                .authenticationManager(authenticationManager)
                // 这里设置获取token的方式，redis或者jwt,当前使用redis
                .accessTokenConverter(jwtAccessTokenConverter())
                //支持GET  POST  请求获取token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // 必须注入userDetailsService否则根据refresh_token无法加载用户信息
                .userDetailsService(userDetailsService)
                // 开启刷新token
                .reuseRefreshTokens(true);
    }


    /**
     * 认证服务器的安全配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                // isAuthenticated():排除anonymous   isFullyAuthenticated():排除anonymous以及remember-me
                .checkTokenAccess("isAuthenticated()")
                // 允许表单认证
                .allowFormAuthenticationForClients();
    }

    /**
     * 基于redis获取token
     *
     * @return
     */
    @Bean
    public DefaultAccessTokenConverter accessTokenConverter() {
        DefaultAccessTokenConverter converter = new DefaultAccessTokenConverter();
        converter.setIncludeGrantType(true);

        return converter;
    }

    /**
     * 基于redis实现令牌（Access Token）
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        // 基于redis实现令牌（Access Token）
        return new RedisTokenStore(redisConnection);
    }

    /**
     * 基于jwt获取token
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("bcrypt");
/***
 //			 * 重写增强token方法,用于自定义一些token返回的信息
 //			 */
//			@Override
//			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//				String userName = authentication.getUserAuthentication().getName();
//				User user = (User) authentication.getUserAuthentication().getPrincipal();// 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
//				/** 自定义一些token属性 ***/
//				final Map<String, Object> additionalInformation = new HashMap<>();
//				additionalInformation.put("userName", userName);
//				additionalInformation.put("roles", user.getAuthorities());
//				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
//				OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
//				return enhancedToken;
//			}

        return converter;
    }

    /**
     * 基于jwt实现令牌（Access Token）
     *
     * @return
     */
    @Bean
    public TokenStore jwtTokenStore() {
        //基于jwt实现令牌（Access Token）
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 资源认证服务使用
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = ResourceServerTokenServices.class)
    public ResourceServerTokenServices tokenService() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(jwtTokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

}
