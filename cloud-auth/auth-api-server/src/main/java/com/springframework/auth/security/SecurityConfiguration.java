package com.springframework.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * @author summer
 * @version 2.0
 * @Description Security 配置
 * @Date Created in 2018/12/7 9:41
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    @Bean
    @ConditionalOnMissingBean(value = AuthenticationManager.class)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .and().csrf().disable();
        http.csrf().disable()
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-resources/**","/login", "/oauth/**", "/swagger-ui.html", "/webjars/**","/v2/api-docs").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .failureUrl("/login-error")
                .successForwardUrl("/index")
                .and()
                .logout()
                .addLogoutHandler(new SecurityContextLogoutHandler())
                .logoutSuccessUrl("/index")
                .clearAuthentication(true)
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and()
                .sessionManagement()
                .maximumSessions(3)
                .expiredUrl("/auto/login");

    }
        /*http.csrf().disable().authorizeRequests()
                // 排除登陆 /auto/login 路径的拦截
                .antMatchers("/auto/login", "/oauth/**").permitAll()
                // 需要权限访问的路径 /user/**
                // .antMatchers("/user/**").hasRole("USER")
                .and().authorizeRequests().anyRequest().authenticated()
                .and().formLogin().permitAll()
                .failureUrl("/login-error").successForwardUrl("/home")
                .and().logout().addLogoutHandler(new SecurityContextLogoutHandler())
                .logoutSuccessUrl("/index").clearAuthentication(true).permitAll()
                .and().sessionManagement().maximumSessions(3).expiredUrl("/auto/login");*/
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoderEnhance();
    }
}
