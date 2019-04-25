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
    @ApiModelProperty
    private String username;
    @ApiModelProperty
    private String password;
    @ApiModelProperty
    private String sex;
    @ApiModelProperty
    private String phoneNumber;
    @ApiModelProperty
    private String email;
    @ApiModelProperty
    private String status;
    @ApiModelProperty
    private String introduce;
}
