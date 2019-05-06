package com.springframework.auth.domain.dao.impl;

import com.springframework.auth.domain.po.OauthClientToken;
import com.springframework.auth.domain.mapper.OauthClientTokenMapper;
import com.springframework.auth.domain.dao.OauthClientTokenDao;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */
@Service
public class OauthClientTokenDaoImpl extends ServiceImpl<OauthClientTokenMapper, OauthClientToken> implements OauthClientTokenDao {
	
}
