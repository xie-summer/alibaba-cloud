package com.springframework.user.service;

import com.springframework.user.domain.po.RoleDO;
import com.springframework.user.domain.po.UserDO;

import java.util.List;

/**
 * @author summer
 * 2019/1/15
 */
public interface UserService {
    /**
     * @param username 查询
     * @return
     */
    UserDO getByUserName(String username);

    /**
     * @param username 根据用户名查询角色
     * @return
     */
    List<RoleDO> getRolesByUserName(String username);
}
