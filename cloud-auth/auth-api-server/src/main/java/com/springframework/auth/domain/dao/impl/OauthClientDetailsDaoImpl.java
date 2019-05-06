package com.springframework.auth.domain.dao.impl;

import com.springframework.auth.domain.po.OauthClientDetails;
import com.springframework.auth.domain.mapper.OauthClientDetailsMapper;
import com.springframework.auth.domain.dao.OauthClientDetailsDao;
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
public class OauthClientDetailsDaoImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsDao {
	
}
