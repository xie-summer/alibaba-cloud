package com.springframework.auth.domain.dao.impl;

import com.springframework.auth.domain.po.Clientdetails;
import com.springframework.auth.domain.mapper.ClientdetailsMapper;
import com.springframework.auth.domain.dao.ClientdetailsDao;
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
public class ClientdetailsDaoImpl extends ServiceImpl<ClientdetailsMapper, Clientdetails> implements ClientdetailsDao {
	
}
