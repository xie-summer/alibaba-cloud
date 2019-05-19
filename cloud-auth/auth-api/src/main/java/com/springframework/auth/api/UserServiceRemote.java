package com.springframework.auth.api;

import com.springframework.auth.api.domain.vo.user.UserVO;
import com.springframework.domain.base.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author summer
 */
@Api(value = "用户接口",tags = "用户接口")
@RequestMapping("/auth/user")
public interface UserServiceRemote {
    /**
     * @param username 根据用户名查询用户信息
     * @return
     */
    @GetMapping(value={"/byName"})
    @ApiOperation(value = "根据用户名查询用户",notes = "根据用户名查询用户")
    RestResult<UserVO> getByUserName(@RequestParam("username") String username);
}
