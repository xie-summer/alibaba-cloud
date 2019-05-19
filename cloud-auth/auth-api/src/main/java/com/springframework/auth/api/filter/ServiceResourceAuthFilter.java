package com.springframework.auth.api.filter;

import com.alibaba.fastjson.JSONObject;
import com.springframework.auth.api.configure.ServiceResourceAuthProperties;
import com.springframework.auth.api.constant.JWTConstant;
import com.springframework.auth.api.security.OAuthRequestedMatcher;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author summer
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ServiceResourceAuthFilter extends OncePerRequestFilter {

    private static final OAuthRequestedMatcher MATCHER = new OAuthRequestedMatcher();
    private ServiceResourceAuthProperties serviceResourceAuthProperties;
    private RestTemplate restTemplate;
    private ApplicationContext applicationContext;
    @Value("${spring.application.name:}")
    private String resourceId;

    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        List<String> ignoreUrls = serviceResourceAuthProperties.getIgnoreUrls();
        if (!serviceResourceAuthProperties.getEnable()) {
            filterChain.doFilter(request, response);
            return;
        } else if (!ignoreUrls.isEmpty()) {
            //忽略放行
            List<RequestMatcher> antMatchers = OAuthRequestedMatcher.antMatchers(request.getMethod(), ignoreUrls);
            boolean matches = false;
            for (RequestMatcher antMatcher : antMatchers) {
                matches = antMatcher.matches(request);
                if (matches) {
                    break;
                }
            }
            if (matches) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        String token = MATCHER.getToken(request);
        if (StringUtils.hasText(token)) {
            //校验token  2 种方式, 1 解析jwt 获取权限  2 请求 auth-server 校验token 再解析jwt
            Claims claims = Jwts.parser()
                    .setSigningKey(JWTConstant.secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            if (!StringUtils.hasText(resourceId)) {
                log.error("spring.application.name is not set");
                setOauthSecurityFailResponse(response, "spring.application.name is not set");
            }
            String audience = claims.getAudience();
            List<String> recourceIds = JSONObject.parseArray(audience, String.class);
            if (recourceIds != null && recourceIds.contains(resourceId)) {
                filterChain.doFilter(request, response);
                return;
            }
            setOauthSecurityFailResponse(response);
        } else {
            setOauthSecurityFailResponse(response);
        }
        filterChain.doFilter(request, response);
    }

    private void setOauthSecurityFailResponse(HttpServletResponse response, String errorInfo) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, String> map = new HashMap<>(4);
        map.put("code", "401");
        map.put("message", errorInfo);
        map.put("data", errorInfo);
        try {
            response.getWriter().println(JSONObject.toJSON((map)));
        } catch (IOException var5) {
            //ignore
        }
    }

    private void setOauthSecurityFailResponse(HttpServletResponse response) {
        setOauthSecurityFailResponse(response, "Unauthorized");
    }

    private static List<RequestMatcher> antMatchers(String httpMethod,
                                                    List<String> antPatterns) {
        List<RequestMatcher> matchers = new ArrayList<>();
        for (String pattern : antPatterns) {
            matchers.add(new AntPathRequestMatcher(pattern, httpMethod));
        }
        return matchers;
    }

}
