package com.springframework.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springframework.auth.domain.po.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author summer
 * 2019/1/15
 */
@Mapper
public interface MemberMapper extends BaseMapper<UserDO> {
}
