package com.springframework.auth.domain.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springframework.auth.domain.dao.OauthApprovalsDao;
import com.springframework.auth.domain.mapper.OauthApprovalsMapper;
import com.springframework.auth.domain.po.OauthApprovalsDO;
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
public class OauthApprovalsDaoImpl extends ServiceImpl<OauthApprovalsMapper, OauthApprovalsDO> implements OauthApprovalsDao {
	
}
