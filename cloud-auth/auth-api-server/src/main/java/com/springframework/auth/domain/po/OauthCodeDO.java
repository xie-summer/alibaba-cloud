package com.springframework.auth.domain.po;

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
@TableName("oauth_code")
@EqualsAndHashCode(callSuper = true)
@Data
public class OauthCodeDO extends Model<OauthCodeDO> {

    private static final long serialVersionUID = 1L;

	private String code;
	private byte[] authentication;


	public static final String CODE = "code";
	public static final String AUTHENTICATION = "authentication";

}
