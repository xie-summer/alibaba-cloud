package com.springframework.auth.service.impl;

import com.springframework.auth.api.domain.dto.OauthClientDetailsDTO;
import com.springframework.auth.domain.dao.OauthClientDetailsDao;
import com.springframework.auth.domain.po.OauthClientDetailsDO;
import com.springframework.auth.service.OauthSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author summer
 */
@Service
public class OauthSecurityServiceImpl implements OauthSecurityService {
    private final OauthClientDetailsDao oauthClientDetailsDao;

    @Autowired
    public OauthSecurityServiceImpl(OauthClientDetailsDao oauthClientDetailsDao) {
        this.oauthClientDetailsDao = oauthClientDetailsDao;
    }

    /**
     * @param oauthClientDetails
     * @return
     */
    @Override
    public Integer createOauthClientDetails(@NotNull OauthClientDetailsDTO oauthClientDetails) {
        return oauthClientDetailsDao.createOauthClientDetails(oauthClientDetails);
    }

    /**
     * @param oauthClientDetails
     * @return
     */
    @Override
    public Integer updateOauthClientDetails(@NotNull OauthClientDetailsDTO oauthClientDetails) {
        return oauthClientDetailsDao.updateOauthClientDetails(oauthClientDetails);
    }

    /**
     * @param clientId
     * @return
     */
    @Override
    public OauthClientDetailsDO selectOauthClientDetailsByClientId(@NotNull final String clientId) {
        return oauthClientDetailsDao.selectOauthClientDetailsByClientId(clientId);
    }
}
