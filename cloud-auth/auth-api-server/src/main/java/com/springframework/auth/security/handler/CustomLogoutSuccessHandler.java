package com.springframework.auth.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author summer
 * @version 2.0
 * @Description 退出系统自定义处理
 * @Date Created in 2018/12/7 9:45
 */
public class CustomLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    @Autowired(required = false)
    private TokenStore tokenStore;
    @Autowired(required = false)
    private TokenStore jwtTokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LOGGER.info(" =================  成功退出系统  =================  ");
        final String accessToken = request.getParameter("access_token");
        if (accessToken != null) {
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
            if (oAuth2AccessToken == null) {
                oAuth2AccessToken = jwtTokenStore.readAccessToken(accessToken);
                LOGGER.info("token =" + oAuth2AccessToken.getValue());
                jwtTokenStore.removeAccessToken(oAuth2AccessToken);
                return;
            }
            LOGGER.info("token =" + oAuth2AccessToken.getValue());
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
        //退出信息插入日志记录表中
        //ResultUtil.writeJavaScript(httpServletResponse, ErrorCodeEnum.SUCCESS, "退出系统成功."); //自己封装的代码 作用就是把信息返回给前端去
    }

}
