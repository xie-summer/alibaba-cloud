package com.springframework.feign.configure;

import com.springframework.exception.AuthorizedException;
import com.springframework.feign.annotation.OriginService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author summer
 */
@Aspect
public class OriginServiceAspectImpl implements OriginServiceAspect {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String X_FEIGNORIGIN_HEADER = "X-FeignOrigin";

    /**
     * 权限过滤
     */
    @Override
    @Pointcut("@annotation(com.springframework.feign.annotation.OriginService)")
    public void authenticate() {

    }

    @Around("authenticate()")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        OriginService originService = signature.getMethod().getAnnotation(OriginService.class);
        if (originService != null) {
            String[] names = originService.names();
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String originServiceHeader = request.getHeader(X_FEIGNORIGIN_HEADER);
                boolean authenticate = Arrays.stream(names).anyMatch(one -> one.equalsIgnoreCase(originServiceHeader));
                if (authenticate) {
                    return joinPoint.proceed();
                }
            }
        }
        throw new AuthorizedException();
    }

}
