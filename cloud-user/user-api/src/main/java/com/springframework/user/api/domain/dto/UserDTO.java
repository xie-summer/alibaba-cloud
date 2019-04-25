package com.springframework.user.api.domain.dto;

import com.springframework.domain.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xieshengrong
 */
@Setter
@Getter
@ToString
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = 2698718407513394783L;
    private String username;
    private String password;
    private int sex;
    private String phoneNumber;
    private String email;
    private String status;
    private String introduce;

    @Override
    public Serializable realId() {
        return serialVersionUID;
    }
}
