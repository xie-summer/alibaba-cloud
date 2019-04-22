package com.springframework.auth.security.service.impl;

import com.google.common.collect.Lists;
import com.springframework.auth.domain.po.UserDO;
import com.springframework.auth.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author summer
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = memberService.getByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(), getAuthority());
    }

    private List getAuthority() {
        final SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
        return Lists.newArrayList(roleAdmin);
    }

}
