package com.springframework.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springframework.user.domain.po.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author summer
 * 2019/1/15
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
