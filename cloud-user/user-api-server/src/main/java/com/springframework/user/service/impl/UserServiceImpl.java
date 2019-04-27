package com.springframework.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springframework.user.domain.po.RoleDO;
import com.springframework.user.domain.po.UserDO;
import com.springframework.user.mapper.UserMapper;
import com.springframework.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;

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

    /**
     * @param username 根据用户名查询角色
     * @return
     */
    @Override
    public List<RoleDO> getRolesByUserName(String username) {
        RoleDO query = new RoleDO();
        QueryWrapper<RoleDO> wrapper = new QueryWrapper<>();
        wrapper.eq(RoleDO.USERNAME, username);
        return query.selectList(wrapper);
    }
}
