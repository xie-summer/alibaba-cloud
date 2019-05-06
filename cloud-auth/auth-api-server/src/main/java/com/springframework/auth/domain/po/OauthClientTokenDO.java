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
@TableName("oauth_client_token")
@EqualsAndHashCode(callSuper = true)
@Data
public class OauthClientTokenDO extends Model<OauthClientTokenDO> {

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



	public static final String TOKEN_ID = "token_id";
	public static final String TOKEN = "token";
	public static final String AUTHENTICATION_ID = "authentication_id";
	public static final String USER_NAME = "user_name";
	public static final String CLIENT_ID = "client_id";

}
