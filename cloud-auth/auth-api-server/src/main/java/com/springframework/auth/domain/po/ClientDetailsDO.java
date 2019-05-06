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
@TableName("clientdetails")
@EqualsAndHashCode(callSuper = true)
@Data
public class ClientDetailsDO extends Model<ClientDetailsDO>  {

    private static final long serialVersionUID = 1L;

	private String appId;
	private String resourceIds;
	private String appSecret;
	private String scope;
	private String grantTypes;
	private String redirectUrl;
	private String authorities;
	@TableField("access_token_validity")
	private Integer accessTokenValidity;
	@TableField("refresh_token_validity")
	private Integer refreshTokenValidity;
	private String additionalInformation;
	private String autoApproveScopes;



	public static final String APPID = "appId";

	public static final String RESOURCEIDS = "resourceIds";

	public static final String APPSECRET = "appSecret";

	public static final String SCOPE = "scope";

	public static final String GRANTTYPES = "grantTypes";

	public static final String REDIRECTURL = "redirectUrl";

	public static final String AUTHORITIES = "authorities";

	public static final String ACCESS_TOKEN_VALIDITY = "access_token_validity";

	public static final String REFRESH_TOKEN_VALIDITY = "refresh_token_validity";

	public static final String ADDITIONALINFORMATION = "additionalInformation";

	public static final String AUTOAPPROVESCOPES = "autoApproveScopes";

}
