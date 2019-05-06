package com.springframework.auth.domain.dao.impl;

import com.springframework.auth.domain.po.OauthRefreshToken;
import com.springframework.auth.domain.mapper.OauthRefreshTokenMapper;
import com.springframework.auth.domain.dao.OauthRefreshTokenDao;
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
public class OauthRefreshTokenDaoImpl extends ServiceImpl<OauthRefreshTokenMapper, OauthRefreshToken> implements OauthRefreshTokenDao {
	
}
