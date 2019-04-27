package com.springframework.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springframework.user.domain.po.RoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author summer
 * 2019/1/15
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {
}
