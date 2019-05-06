package com.springframework.auth.domain.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springframework.auth.domain.dao.OauthCodeDao;
import com.springframework.auth.domain.mapper.OauthCodeMapper;
import com.springframework.auth.domain.po.OauthCodeDO;
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
public class OauthCodeDaoImpl extends ServiceImpl<OauthCodeMapper, OauthCodeDO> implements OauthCodeDao {
	
}
