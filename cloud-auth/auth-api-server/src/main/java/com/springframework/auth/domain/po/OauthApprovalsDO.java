package com.springframework.auth.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author summer
 * @since 2019-05-06
 */
@TableName("oauth_approvals")
@EqualsAndHashCode(callSuper = true)
@Data
public class OauthApprovalsDO  extends Model<OauthApprovalsDO> {

    private static final long serialVersionUID = 1L;

	private String userId;
	private String clientId;
	private String scope;
	private String status;
	private Date expiresAt;
	private Date lastModifiedAt;



	public static final String USERID = "userId";
	public static final String CLIENTID = "clientId";
	public static final String SCOPE = "scope";
	public static final String STATUS = "status";
	public static final String EXPIRESAT = "expiresAt";
	public static final String LASTMODIFIEDAT = "lastModifiedAt";

}
