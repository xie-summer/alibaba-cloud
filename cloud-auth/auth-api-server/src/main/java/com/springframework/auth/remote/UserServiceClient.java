package com.springframework.auth.remote;

import com.springframework.mvc.util.RestResult;
import com.springframework.user.api.UserServiceRemote;
import com.springframework.user.api.domain.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author summer
 */
@FeignClient(value = "user-server", fallback = UserServiceClient.UserServiceFallback.class)
public interface UserServiceClient extends UserServiceRemote {
    class UserServiceFallback implements UserServiceClient {
        /**
         * @param username 根据用户名查询用户信息
         * @return
         */
        @Override
        public RestResult<UserVO> getByUserName(String username) {
            throw new RuntimeException("no fallback method");
        }
    }
}
