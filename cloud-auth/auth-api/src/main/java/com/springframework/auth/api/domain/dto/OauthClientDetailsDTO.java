package com.springframework.auth.api.domain.dto;

import com.springframework.domain.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */

@Getter
@Setter
@ToString
@ApiModel
public class OauthClientDetailsDTO extends BaseDTO {

	private static final long serialVersionUID = 4702603125651330951L;

    @ApiModelProperty("client_id")
	private String clientId;
	@ApiModelProperty("resource_ids")
	@NotEmpty(message = "资源ids不能为空")
	private String resourceIds;
	@ApiModelProperty("client_secret")
	private String clientSecret;
	@ApiModelProperty("scope")
	@NotEmpty
	private String scope;
	@ApiModelProperty("authorized_grant_types")
	@NotEmpty
	private String authorizedGrantTypes;
	@ApiModelProperty("web_server_redirect_uri")
	@NotEmpty
	private String webServerRedirectUri;
	@ApiModelProperty("authorities")
	@NotEmpty
	private String authorities;
	@ApiModelProperty("access_token_validity")
	@NotEmpty
	private Integer accessTokenValidity;
	@ApiModelProperty("refresh_token_validity")
	@NotEmpty
	private Integer refreshTokenValidity;
	@ApiModelProperty("additional_information")
	@NotEmpty(message = " application additional_information not empty")
	private String additionalInformation;
	@ApiModelProperty("是否跳过认证过程 true/false")
	private String autoapprove;

	@Override
	public Serializable realId() {
		return serialVersionUID;
	}
}
