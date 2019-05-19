package com.springframework.auth.api.security;

import com.springframework.auth.api.configure.ServiceResourceAuthProperties;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * @author summer
 */
public class OAuthRequestedMatcher implements RequestMatcher {
    public static final String BEARER = "Bearer";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String TOKEN = "token";
    public static final String AUTHORIZATION = "Authorization";
    private ServiceResourceAuthProperties serviceResourceAuthProperties;

    public OAuthRequestedMatcher(ServiceResourceAuthProperties serviceResourceAuthProperties) {
        this.serviceResourceAuthProperties = serviceResourceAuthProperties;
    }

    public OAuthRequestedMatcher() {
    }

    /**
     * @param request
     * @return
     */
    private boolean ignoreMatche(HttpServletRequest request) {
        if (serviceResourceAuthProperties == null) {
            serviceResourceAuthProperties = new ServiceResourceAuthProperties();
        }
        List<String> ignoreUrls = serviceResourceAuthProperties.getIgnoreUrls();
        //忽略放行
        List<RequestMatcher> antMatchers = OAuthRequestedMatcher.antMatchers(request.getMethod(), ignoreUrls);
        boolean matches = false;
        for (RequestMatcher antMatcher : antMatchers) {
            matches = antMatcher.matches(request);
            if (matches) {
                break;
            }
        }
        return matches;
    }

    public static List<RequestMatcher> antMatchers(String httpMethod,
                                                   List<String> antPatterns) {
        List<RequestMatcher> matchers = new ArrayList<>();
        for (String pattern : antPatterns) {
            matchers.add(new AntPathRequestMatcher(pattern, httpMethod));
        }
        return matchers;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (ignoreMatche(request)) {
            return true;
        }
        String auth = request.getHeader(AUTHORIZATION);
        // Determine if the client request contained an OAuth Authorization
        boolean haveOauth2Token = (auth != null) && auth.startsWith(BEARER);
        boolean haveAccessToken = request.getParameter(ACCESS_TOKEN) != null;
        boolean haveToken = request.getParameter(TOKEN) != null;
        return haveOauth2Token || haveAccessToken || haveToken;
    }

    public String getToken(HttpServletRequest request) {
        String auth = request.getHeader(AUTHORIZATION);
        boolean haveOauth2Token = (auth != null) && auth.startsWith(BEARER);
        if (haveOauth2Token) {
            return auth;
        }
        boolean haveAccessToken = request.getParameter(ACCESS_TOKEN) != null;
        if (haveAccessToken) {
            return request.getParameter(ACCESS_TOKEN);
        }
        return "";
    }

}
