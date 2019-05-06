package com.springframework.auth.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springframework.auth.domain.po.ClientDetailsDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */
@Mapper
public interface ClientDetailsMapper extends BaseMapper<ClientDetailsDO> {

}