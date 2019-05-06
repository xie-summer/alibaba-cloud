package com.springframework.auth.domain.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */
@TableName("oauth_client_details")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("client_id")
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


	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

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
