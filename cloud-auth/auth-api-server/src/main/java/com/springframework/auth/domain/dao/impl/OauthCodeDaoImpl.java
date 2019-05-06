package com.springframework.auth.domain.dao.impl;

import com.springframework.auth.domain.po.OauthCode;
import com.springframework.auth.domain.mapper.OauthCodeMapper;
import com.springframework.auth.domain.dao.OauthCodeDao;
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
public class OauthCodeDaoImpl extends ServiceImpl<OauthCodeMapper, OauthCode> implements OauthCodeDao {
	
}
