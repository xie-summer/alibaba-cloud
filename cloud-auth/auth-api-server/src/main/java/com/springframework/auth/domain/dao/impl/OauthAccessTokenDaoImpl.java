package com.springframework.auth.domain.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.springframework.auth.domain.dao.OauthAccessTokenDao;
import com.springframework.auth.domain.mapper.OauthAccessTokenMapper;
import com.springframework.auth.domain.po.OauthAccessTokenDO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */
@Repository
public class OauthAccessTokenDaoImpl extends ServiceImpl<OauthAccessTokenMapper, OauthAccessTokenDO> implements OauthAccessTokenDao {
	
}
