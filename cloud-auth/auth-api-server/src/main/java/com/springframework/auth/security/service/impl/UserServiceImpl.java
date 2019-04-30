package com.springframework.auth.security.service.impl;

import com.google.common.base.Joiner;
import com.springframework.auth.remote.UserServiceClient;
import com.springframework.domain.base.RestResult;
import com.springframework.user.api.domain.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author summer
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RestResult<UserVO> user = userServiceClient.getByUserName(username);
        if (user.getData() == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getData().getUsername(), user.getData().getPassword(), getAuthority(user.getData()));
    }

    private Collection<? extends GrantedAuthority> getAuthority(UserVO user) {
        List<String> roles = user.getRoles();
        if (CollectionUtils.isNotEmpty(roles)) {
            String roleStr = Joiner.on(",").join(roles);
            return AuthorityUtils.commaSeparatedStringToAuthorityList(roleStr);
        }
        return new ArrayList<>();
    }

}
