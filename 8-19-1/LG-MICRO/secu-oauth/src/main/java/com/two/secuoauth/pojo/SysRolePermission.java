package com.two.secuoauth.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.two.core.base.jdbc.entity.GlobalEntity;

/**
 * 功能描述: 用户\角色关联表 jpa只用于建表,所有关系不用jpa创建,避免使用jpa做其他操作
 *
 * @author: 田杰
 * @create: 2018-05-10 10:12
 **/
@Entity
@Table(name = "lg_role_permission")
public class SysRolePermission extends GlobalEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "sys_role_id")
    private Long sysRoleId;

    @Column(name = "sys_permission_id")
    private Long sysPermissionId;

	public Long getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public Long getSysPermissionId() {
		return sysPermissionId;
	}

	public void setSysPermissionId(Long sysPermissionId) {
		this.sysPermissionId = sysPermissionId;
	}
}
