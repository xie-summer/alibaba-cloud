package com.springframework.auth.domain.po;

import java.util.Date;
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
@TableName("oauth_approvals")
public class OauthApprovals implements Serializable {

    private static final long serialVersionUID = 1L;

	private String userId;
	private String clientId;
	private String scope;
	private String status;
	private Date expiresAt;
	private Date lastModifiedAt;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public static final String USERID = "userId";

	public static final String CLIENTID = "clientId";

	public static final String SCOPE = "scope";

	public static final String STATUS = "status";

	public static final String EXPIRESAT = "expiresAt";

	public static final String LASTMODIFIEDAT = "lastModifiedAt";

}
