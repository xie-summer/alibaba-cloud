package com.springframework.auth.domain.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springframework.auth.domain.dao.OauthClientTokenDao;
import com.springframework.auth.domain.mapper.OauthClientTokenMapper;
import com.springframework.auth.domain.po.OauthClientTokenDO;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */
@Repository
public class OauthClientTokenDaoImpl extends ServiceImpl<OauthClientTokenMapper, OauthClientTokenDO> implements OauthClientTokenDao {
	
}
