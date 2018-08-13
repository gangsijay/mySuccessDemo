package com.two.lgums.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.two.core.base.jdbc.entity.GlobalEntity;


/**
 * 功能描述: ${description}
 *
 * @author: 田杰
 * @create: 2018-05-09 15:45
 **/
@Entity
@Table(name = "sys_lg_user")
public class SysUserDO extends GlobalEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6635591138619006341L;
	

	@Column(name = "name",length = 20)
    private String name;

    @Column(name = "code",unique = true,length = 30)
    private String code;
    /***
     *登陆名称-登陆唯一编号
     */
    @Column(name = "login_name",unique = true,length = 20)
    private String loginName;


    @Column(name = "password",length = 40)
    private String password;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
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

}
