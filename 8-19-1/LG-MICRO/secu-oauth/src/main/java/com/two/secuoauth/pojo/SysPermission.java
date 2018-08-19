package com.two.secuoauth.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.two.core.base.jdbc.entity.GlobalEntity;

/**
 * 系统权限
 *
 * @Author: 我爱大金子
 * @Description: 系统权限
 * @Date: Create in 17:04 2017/7/5
 */
@Entity
@Table(name = "lg_permission")
public class SysPermission extends GlobalEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//权限名称
	@Column(name = "name", length = 30)
    private String name;
 
    //权限描述
	@Column(name = "descritpion", length = 130)
    private String descritpion;
 
    //授权链接
	@Column(name = "url", length = 130)
    private String url;
 
    //父节点id
	@Column
    private Long pid;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getDescritpion() {
        return descritpion;
    }
 
    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }
 
    public String getUrl() {
        return url;
    }
 
    public void setUrl(String url) {
        this.url = url;
    }
 
    public Long getPid() {
        return pid;
    }
 
    public void setPid(Long pid) {
        this.pid = pid;
    }
}