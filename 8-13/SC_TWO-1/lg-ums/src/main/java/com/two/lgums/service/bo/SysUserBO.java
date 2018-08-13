package com.two.lgums.service.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述: ${description}
 *
 * @author: 田杰
 * @create: 2018-05-11 10:35
 **/
public class SysUserBO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String loginName;

    private String password;
    
    private List<SysRoleBO> roleList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SysRoleBO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRoleBO> roleList) {
		this.roleList = roleList;
	}

}
