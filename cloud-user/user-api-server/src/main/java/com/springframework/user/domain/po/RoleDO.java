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
@TableName("t_roles")
public class RoleDO extends BaseEntity<RoleDO> {
    public static final String USERNAME = "username";
    public static final String ROLE = "role";
    @TableField("username")
    private String username;
    @TableField("role")
    private String role;
}
