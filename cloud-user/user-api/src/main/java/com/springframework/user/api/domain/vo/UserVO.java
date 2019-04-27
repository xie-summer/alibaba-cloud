package com.springframework.user.api.domain.vo;

import com.springframework.domain.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author summer
 */
@Setter
@Getter
@ToString
@ApiModel
public class UserVO extends BaseVo {
    private static final long serialVersionUID = 6580085273880483175L;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("手机号码")
    private String phoneNumber;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("状态0正常1无效")
    private String status;
    @ApiModelProperty("简介")
    private String introduce;
}
