package com.springframework.auth.api.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author summer
 * 2019/4/30
 */
@Data
public class AccessTokenVO {
    @JSONField(name="access_token")
    @JsonProperty("access_token")
    private String accessToken;
    @JSONField(name="token_type")
    @JsonProperty("token_type")
    private String tokenType;
    @JSONField(name="expires_in")
    @JsonProperty("expires_in")
    private Long expiresIn;
    private String scope;
    private List<Roles> roles;
    private UserVO user;
    private String jti;
}