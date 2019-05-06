package com.springframework.auth.domain.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springframework.auth.domain.dao.OauthClientDetailsDao;
import com.springframework.auth.domain.mapper.OauthClientDetailsMapper;
import com.springframework.auth.domain.po.OauthClientDetailsDO;
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
public class OauthClientDetailsDaoImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetailsDO> implements OauthClientDetailsDao {
	
}
