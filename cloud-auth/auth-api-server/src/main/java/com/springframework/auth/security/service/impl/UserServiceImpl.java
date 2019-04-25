package com.springframework.auth.security.service.impl;

import com.google.common.collect.Lists;
import com.springframework.auth.remote.UserServiceClient;
import com.springframework.user.api.domain.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author summer
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO user = userServiceClient.getByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private Collection<? extends GrantedAuthority> getAuthority() {
        final SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
        //TODO  查找权限
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("");
        return Lists.newArrayList(roleAdmin);
    }

}
