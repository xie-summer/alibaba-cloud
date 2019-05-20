package com.springframework.auth.api.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author summer
 */
@Data
public class UserVO {
    private String password;
    private String username;
    private List<Authorities> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

}
