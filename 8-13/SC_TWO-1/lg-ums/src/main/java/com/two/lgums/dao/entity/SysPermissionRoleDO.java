package com.two.lgums.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.two.core.base.jdbc.entity.GlobalEntity;

/**
 * 功能描述: 权限角色关联表
 *
 * @author: 田杰
 * @create: 2018-05-10 11:36
 **/
@Entity
@Table(name = "sys_lg_permission_role")
public class SysPermissionRoleDO extends GlobalEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "sys_permission_id")
    private Long sysPermissionId;

    @Column(name = "sys_role_id")
    private Long sysRoleId;

	public Long getSysPermissionId() {
		return sysPermissionId;
	}

	public void setSysPermissionId(Long sysPermissionId) {
		this.sysPermissionId = sysPermissionId;
	}

	public Long getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

}
