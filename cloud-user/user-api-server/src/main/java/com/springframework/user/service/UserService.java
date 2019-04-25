package com.springframework.user.service;

import com.springframework.user.domain.po.UserDO;

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
}
