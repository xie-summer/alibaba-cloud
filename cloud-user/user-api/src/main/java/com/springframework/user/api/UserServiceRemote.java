package com.springframework.user.api;

import com.springframework.user.api.domain.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "用户接口",tags = "用户接口")
@RequestMapping("/user")
public interface UserServiceRemote {
    /**
     * @param username 根据用户名查询用户信息
     * @return
     */
    @GetMapping(value={"/byName"})
//    @RequestMapping(value = "/byName",method= RequestMethod.GET)
    @ApiOperation(value = "根据用户名查询用户",notes = "根据用户名查询用户")
    UserVO getByUserName(@RequestParam("username") String username);
}
