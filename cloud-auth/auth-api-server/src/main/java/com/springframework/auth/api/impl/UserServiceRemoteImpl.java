package com.springframework.auth.api.impl;

import com.springframework.auth.api.UserServiceRemote;
import com.springframework.auth.api.domain.vo.user.UserVO;
import com.springframework.auth.remote.UserServiceClient;
import com.springframework.domain.base.RestResult;
import com.springframework.domain.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author summer
 * 2018/11/20
 */
@RestController
public class UserServiceRemoteImpl implements UserServiceRemote {

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    public UserServiceRemoteImpl(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    /**
     * @param username 根据用户名查询用户信息
     * @return
     */
    @Override
    public RestResult<UserVO> getByUserName(@RequestParam("username") String username) {
        RestResult<com.springframework.user.api.domain.vo.UserVO> byUserName = userServiceClient.getByUserName(username);
        com.springframework.user.api.domain.vo.UserVO userVO = byUserName.getData();
        UserVO user = new UserVO();
        BeanUtil.copyProperties(user,userVO);
        return new RestResult<>(user);
    }
}
