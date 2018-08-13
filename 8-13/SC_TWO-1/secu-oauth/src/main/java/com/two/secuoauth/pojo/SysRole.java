package com.two.secuoauth.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.two.core.base.jdbc.entity.GlobalEntity;

/**
 * 系统角色
 *
 * @Author: 我爱大金子
 * @Description: 系统角色
 * @Date: Create in 16:13 2017/7/5
 */
@Entity
@Table(name = "lg_role")
public class SysRole extends GlobalEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name", length = 30)
    private String name;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}