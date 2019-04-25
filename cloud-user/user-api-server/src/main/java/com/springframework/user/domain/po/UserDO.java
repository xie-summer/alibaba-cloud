package com.springframework.user.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springframework.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author summer
 * 2019/1/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_user")
public class UserDO extends BaseEntity<UserDO> {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SEX = "sex";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String EMAIL = "email";
    public static final String STATUS = "status";
    public static final String INTRODUCE = "introduce";
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("sex")
    private String sex;
    @TableField("phone_number")
    private String phoneNumber;
    @TableField("email")
    private String email;
    @TableField("status")
    private String status;
    @TableField("introduce")
    private String introduce;
}
