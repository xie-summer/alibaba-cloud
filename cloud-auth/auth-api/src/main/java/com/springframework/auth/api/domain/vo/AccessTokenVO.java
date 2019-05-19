package com.springframework.auth.api.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author summer
 * 2019/4/30
 */
@Data
public class AccessTokenVO {
    @JSONField(name="access_token")
    private String accessToken;
    @JSONField(name="token_type")
    private String tokenType;
    @JSONField(name="expires_in")
    private long expiresn;
    private String scope;
    private List<Roles> roles;
    private UserVO user;
    private String jti;

    @Data
    private static class UserVO {
        private String password;
        private String username;
        private List<Authorities> authorities;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;

    }

    @Data
    private static class Roles {
        private String authority;
    }

    @Data
    private static class Authorities {
        private String authority;
    }
}