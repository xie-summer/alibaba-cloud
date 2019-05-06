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
@TableName("oauth_access_token")
@EqualsAndHashCode(callSuper = true)
@Data
public class OauthAccessTokenDO extends Model<OauthAccessTokenDO> {

    private static final long serialVersionUID = 1L;

	@TableField("token_id")
	private String tokenId;
	private byte[] token;
    @TableField("authentication_id")
	private String authenticationId;
	@TableField("user_name")
	private String userName;
	@TableField("client_id")
	private String clientId;
	private byte[] authentication;
	@TableField("refresh_token")
	private String refreshToken;



	public static final String TOKEN_ID = "token_id";
	public static final String TOKEN = "token";
	public static final String AUTHENTICATION_ID = "authentication_id";
	public static final String USER_NAME = "user_name";
	public static final String CLIENT_ID = "client_id";
	public static final String AUTHENTICATION = "authentication";
	public static final String REFRESH_TOKEN = "refresh_token";

}
