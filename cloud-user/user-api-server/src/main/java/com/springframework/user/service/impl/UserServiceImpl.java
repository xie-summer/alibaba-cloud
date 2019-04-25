package com.springframework.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springframework.user.domain.po.UserDO;
import com.springframework.user.mapper.UserMapper;
import com.springframework.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

/**
 * @author summer
 * 2019/1/15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    /**
     * @param username 查询
     * @return
     */
    @Override
    public UserDO getByUserName(@NotEmpty String username) {
        UserDO query = new UserDO();
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq(UserDO.USERNAME, username);
        return query.selectOne(wrapper);
    }
}
