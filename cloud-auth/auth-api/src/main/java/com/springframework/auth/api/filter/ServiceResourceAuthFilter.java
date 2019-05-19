package com.springframework.auth.api.filter;

import com.springframework.auth.api.configure.ServiceResourceAuthProperties;
import com.springframework.auth.api.security.OAuthRequestedMatcher;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
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
import java.time.LocalDateTime;
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
            List<RequestMatcher> antMatchers = ServiceResourceAuthFilter.antMatchers(request.getMethod(), ignoreUrls);
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
            //校验token
        } else {
            setOauthSecurityFailResponse(response);
        }
        filterChain.doFilter(request, response);
    }

    private void setOauthSecurityFailResponse(HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        //自定义返回数据格式
        Map<String, String> map = new HashMap<>(4);
        map.put("code", "401");
        map.put("message", "Unauthorized");
        map.put("data", "Unauthorized");
        map.put("timestamp", String.valueOf(LocalDateTime.now()));
        try {
            response.getWriter().println(JSONObject.toJSONString(map));
        } catch (IOException var5) {
            //ignore
        }
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
