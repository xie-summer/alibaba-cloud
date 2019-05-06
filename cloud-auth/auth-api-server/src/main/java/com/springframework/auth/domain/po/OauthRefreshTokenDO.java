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
@TableName("oauth_refresh_token")
@EqualsAndHashCode(callSuper = true)
@Data
public class OauthRefreshTokenDO extends Model<OauthRefreshTokenDO> {

    private static final long serialVersionUID = 1L;

	@TableField("token_id")
	private String tokenId;
	private byte[] token;
	private byte[] authentication;



	public static final String TOKEN_ID = "token_id";
	public static final String TOKEN = "token";
	public static final String AUTHENTICATION = "authentication";

}
