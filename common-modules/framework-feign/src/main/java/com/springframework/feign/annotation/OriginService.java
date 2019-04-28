package com.springframework.feign.annotation;

import java.lang.annotation.*;

/**
 * 通过@OriginService注解的服务商
 *
 * @author summer  feign
 * 2018/7/31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Inherited
@Documented
public @interface OriginService {
    String[] names() default {};
}