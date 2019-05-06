package com.springframework.auth.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */
@TableName("oauth_client_details")
@EqualsAndHashCode(callSuper = true)
@Data
public class OauthClientDetailsDO extends Model<OauthClientDetailsDO> {

    private static final long serialVersionUID = 1L;

    @TableField("client_id")
	private String clientId;
	@TableField("resource_ids")
	private String resourceIds;
	@TableField("client_secret")
	private String clientSecret;
	private String scope;
	@TableField("authorized_grant_types")
	private String authorizedGrantTypes;
	@TableField("web_server_redirect_uri")
	private String webServerRedirectUri;
	private String authorities;
	@TableField("access_token_validity")
	private Integer accessTokenValidity;
	@TableField("refresh_token_validity")
	private Integer refreshTokenValidity;
	@TableField("additional_information")
	private String additionalInformation;
	private String autoapprove;



	public static final String CLIENT_ID = "client_id";
	public static final String RESOURCE_IDS = "resource_ids";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String SCOPE = "scope";
	public static final String AUTHORIZED_GRANT_TYPES = "authorized_grant_types";
	public static final String WEB_SERVER_REDIRECT_URI = "web_server_redirect_uri";
	public static final String AUTHORITIES = "authorities";
	public static final String ACCESS_TOKEN_VALIDITY = "access_token_validity";
	public static final String REFRESH_TOKEN_VALIDITY = "refresh_token_validity";
	public static final String ADDITIONAL_INFORMATION = "additional_information";
	public static final String AUTOAPPROVE = "autoapprove";

}
