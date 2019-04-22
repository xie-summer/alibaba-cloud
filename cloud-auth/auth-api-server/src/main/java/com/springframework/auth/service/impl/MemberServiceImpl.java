package com.springframework.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springframework.auth.domain.po.UserDO;
import com.springframework.auth.mapper.MemberMapper;
import com.springframework.auth.service.MemberService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

/**
 * @author summer
 * 2019/1/15
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, UserDO> implements MemberService {

    /**
     * @param username 查询
     * @return
     */
    @Override
    public UserDO getByUserName(@NotEmpty String username) {
        UserDO query = new UserDO();
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        return query.selectOne(wrapper);
    }
}
