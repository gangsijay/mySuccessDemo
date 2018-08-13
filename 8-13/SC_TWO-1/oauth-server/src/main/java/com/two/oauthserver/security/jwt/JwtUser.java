package com.two.oauthserver.security.jwt;

import java.io.Serializable;
import java.util.List;

import com.two.core.base.common.enums.StatusEnum;
import com.two.oauthserver.security.dao.entity.SysRoleDO;

/**
 * 功能描述:
 *
 * @author: 田杰
 * @create: 2018-05-14 10:33
 **/
public class JwtUser  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String loginName;
    private String username;
    private StatusEnum status;
    private List<SysRoleDO> roleList;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public List<SysRoleDO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<SysRoleDO> roleList) {
		this.roleList = roleList;
	}

}
