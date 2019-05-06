package com.springframework.auth.domain.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springframework.auth.api.domain.dto.OauthClientDetailsDTO;
import com.springframework.auth.domain.dao.OauthClientDetailsDao;
import com.springframework.auth.domain.mapper.OauthClientDetailsMapper;
import com.springframework.auth.domain.po.OauthClientDetailsDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */
@Repository
public class OauthClientDetailsDaoImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetailsDO> implements OauthClientDetailsDao {

    /**
     * @param clientId clientId 唯一
     * @return
     */
    @Override
    public OauthClientDetailsDO selectOauthClientDetailsByClientId(String clientId) {
        OauthClientDetailsDO query = new OauthClientDetailsDO();
        QueryWrapper<OauthClientDetailsDO> wrapper = new QueryWrapper<>();
        wrapper.eq(OauthClientDetailsDO.CLIENT_ID, clientId);
        return query.selectOne(wrapper);
    }

    /**
     * @param oauthClientDetails update client detail
     * @return
     */
    @Override
    public Integer updateOauthClientDetails(OauthClientDetailsDTO oauthClientDetails) {
        OauthClientDetailsDO update = new OauthClientDetailsDO();
        BeanUtils.copyProperties(oauthClientDetails, update);
        QueryWrapper<OauthClientDetailsDO> wrapper = new QueryWrapper<>();
        wrapper.eq(OauthClientDetailsDO.CLIENT_ID, oauthClientDetails.getClientId());
        return super.baseMapper.update(update, wrapper);
    }

    /**
     * @param oauthClientDetails create client detail
     * @return
     */
    @Override
    public Integer createOauthClientDetails(OauthClientDetailsDTO oauthClientDetails) {
        OauthClientDetailsDO create = new OauthClientDetailsDO();
        BeanUtils.copyProperties(oauthClientDetails, create);
        return super.baseMapper.insert(create);
    }
}
