package com.springframework.auth.service;

import com.springframework.auth.api.domain.dto.OauthClientDetailsDTO;
import com.springframework.auth.domain.po.OauthClientDetailsDO;

/**
 * @author summer  授权认证基础数据服务类
 */
public interface OauthSecurityService {
    /**
     * @param oauthClientDetails
     * @return
     */
    Integer createOauthClientDetails(OauthClientDetailsDTO oauthClientDetails);

    /**
     * @param oauthClientDetails
     * @return
     */
    Integer updateOauthClientDetails(OauthClientDetailsDTO oauthClientDetails);

    /**
     * @param clientId
     * @return
     */
    OauthClientDetailsDO selectOauthClientDetailsByClientId(String clientId);
}
