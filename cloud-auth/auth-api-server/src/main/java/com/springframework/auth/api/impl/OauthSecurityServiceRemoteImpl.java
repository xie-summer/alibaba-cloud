package com.springframework.auth.api.impl;

import com.google.common.base.Preconditions;
import com.springframework.auth.api.OauthSecurityServiceRemote;
import com.springframework.auth.api.domain.dto.OauthClientDetailsDTO;
import com.springframework.auth.domain.po.OauthClientDetailsDO;
import com.springframework.auth.service.OauthSecurityService;
import com.springframework.domain.base.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author summer
 */
@RestController
public class OauthSecurityServiceRemoteImpl implements OauthSecurityServiceRemote {
    private final OauthSecurityService oauthSecurityService;

    @Autowired
    public OauthSecurityServiceRemoteImpl(OauthSecurityService oauthSecurityService) {
        this.oauthSecurityService = oauthSecurityService;
    }

    @Override
    public RestResult<Integer> createOauthClientDetails(@RequestBody OauthClientDetailsDTO oauthClientDetails) {
        oauthClientDetails.setClientId(UUID.randomUUID().toString());
        oauthClientDetails.setClientSecret(UUID.randomUUID().toString());
        Integer result = oauthSecurityService.createOauthClientDetails(oauthClientDetails);
        return new RestResult<>(result);
    }

    @Override
    public RestResult<Integer> updateOauthClientDetails(@RequestBody OauthClientDetailsDTO oauthClientDetails) {
        OauthClientDetailsDO clientDetailsDO = oauthSecurityService.selectOauthClientDetailsByClientId(oauthClientDetails.getClientId());
        Preconditions.checkNotNull(clientDetailsDO, String.format("clientId : [%s] not exist", oauthClientDetails.getClientId()));
        Integer result = oauthSecurityService.updateOauthClientDetails(oauthClientDetails);
        return new RestResult<>(result);
    }

    @Override
    public RestResult<OauthClientDetailsDTO> list(@RequestBody OauthClientDetailsDTO dto,
                                                  @RequestParam(value = "index") int pageIndex,
                                                  @RequestParam(value = "size") int pageSize) {
        return new RestResult<>(null);
    }

    @Override
    public RestResult<Integer> deleteByPrimaryKey(@RequestParam(value = "id") Long id) {

        return new RestResult<>(null);
    }

    @Override
    public RestResult<Integer> batchDeleteByPrimaryKey(@RequestBody List<Long> ids) {

        return new RestResult<>(null);

    }
}
