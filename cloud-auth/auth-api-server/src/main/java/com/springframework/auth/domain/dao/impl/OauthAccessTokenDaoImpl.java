package com.springframework.auth.domain.dao.impl;

import com.springframework.auth.domain.po.OauthAccessToken;
import com.springframework.auth.domain.mapper.OauthAccessTokenMapper;
import com.springframework.auth.domain.dao.OauthAccessTokenDao;
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
public class OauthAccessTokenDaoImpl extends ServiceImpl<OauthAccessTokenMapper, OauthAccessToken> implements OauthAccessTokenDao {
	
}
