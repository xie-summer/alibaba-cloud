package com.springframework.auth.domain.dao;

import com.springframework.auth.api.domain.dto.OauthClientDetailsDTO;
import com.springframework.auth.domain.po.OauthClientDetailsDO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */
public interface OauthClientDetailsDao  {

    /**
     * @param clientId clientId 唯一
     * @return
     */
    OauthClientDetailsDO selectOauthClientDetailsByClientId(String clientId);

    /**
     * @param oauthClientDetails update client detail
     * @return
     */
    Integer updateOauthClientDetails(OauthClientDetailsDTO oauthClientDetails);

    /**
     * @param oauthClientDetails create client detail
     * @return
     */
    Integer createOauthClientDetails(OauthClientDetailsDTO oauthClientDetails);
}
