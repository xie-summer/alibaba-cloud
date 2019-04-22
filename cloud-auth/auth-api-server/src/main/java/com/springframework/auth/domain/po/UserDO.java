package com.springframework.auth.domain.po;

import com.springframework.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author summer
 * 2019/1/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDO extends BaseEntity<UserDO> {
    private String userName;
    private String passWord;
    private String sex;
    private String phoneNumber;
    private String email;
    private String status;
    private String introduce;
}
