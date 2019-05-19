package com.springframework.auth.api.security;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;


/**
 * @author summer
 */
public class OAuthRequestedMatcher implements RequestMatcher {
    public static final String BEARER = "Bearer";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String AUTHORIZATION = "Authorization";
    @Override
    public boolean matches(HttpServletRequest request) {
        String auth = request.getHeader(AUTHORIZATION);
        // Determine if the client request contained an OAuth Authorization
        boolean haveOauth2Token = (auth != null) && auth.startsWith(BEARER);
        boolean haveAccessToken = request.getParameter(ACCESS_TOKEN) != null;
        return haveOauth2Token || haveAccessToken;
    }

    public String getToken(HttpServletRequest request) {
        String auth = request.getHeader(AUTHORIZATION);
        boolean haveOauth2Token = (auth != null) && auth.startsWith(BEARER);
        if(haveOauth2Token){
            return auth;
        }
        boolean haveAccessToken = request.getParameter(ACCESS_TOKEN) != null;
        if(haveAccessToken){
            return request.getParameter(ACCESS_TOKEN);
        }
        return "";
    }

}
