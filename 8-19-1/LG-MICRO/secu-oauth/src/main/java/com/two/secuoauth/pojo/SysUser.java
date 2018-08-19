package com.two.secuoauth.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.two.core.base.jdbc.entity.GlobalEntity;
 
/**
 * 系统用户
 *
 * @Author: 我爱大金子
 * @Description: 系统用户
 * @Date: Create in 16:12 2017/7/5
 */
@Entity
@Table(name = "lg_user")
public class SysUser  extends GlobalEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "username", length = 30)
    private String username;
	@Column(name = "password", length = 30)
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}