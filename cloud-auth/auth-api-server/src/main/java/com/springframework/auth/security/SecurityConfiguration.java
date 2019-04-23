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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.annotation.Resource;

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
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                // 排查认证，/auto/** 路径的拦截，有其他路径，这里添加即可
                .antMatchers("/login", "/oauth/**").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().formLogin().permitAll()
                .failureUrl("/login-error").successForwardUrl("/index")
                .and().logout().addLogoutHandler(new SecurityContextLogoutHandler())
                .logoutSuccessUrl("/index").clearAuthentication(true).permitAll()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());


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
    }


    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
