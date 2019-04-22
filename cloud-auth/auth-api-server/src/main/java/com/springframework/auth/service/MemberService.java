package com.springframework.auth.service;

import com.springframework.auth.domain.po.UserDO;

/**
 * @author summer
 * 2019/1/15
 */
public interface MemberService {
    /**
     * @param username 查询
     * @return
     */
    UserDO getByUserName(String username);
}
