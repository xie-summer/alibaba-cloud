package com.springframework.sso.remote;

import com.springframework.domain.base.RestResult;
import com.springframework.user.api.domain.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author summer
 */
@FeignClient(value = "user-server")
public interface UserServiceClient  {
    @RequestMapping(value={"/user/byName"},method = RequestMethod.GET)
    @ApiOperation(value = "根据用户名查询用户",notes = "根据用户名查询用户")
    RestResult<UserVO> getByUserName(@RequestParam("username") String username);
}
