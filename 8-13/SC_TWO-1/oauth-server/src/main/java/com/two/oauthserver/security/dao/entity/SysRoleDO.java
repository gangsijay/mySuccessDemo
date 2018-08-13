package com.two.oauthserver.security.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.two.core.base.common.enums.StatusEnum;
import com.two.core.base.jdbc.entity.GlobalEntity;

/**
 * 功能描述: 系统角色表
 *
 * @author: 田杰
 * @create: 2018-05-10 09:35
 **/
@Entity
@Table(name = "sys_role")
public class SysRoleDO extends GlobalEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name", length = 30)
    private String name;

    @Column(name = "code",length = 40)
    private String code;

    @Column(name = "level")
    private Integer level;

    @Column(name = "describe", length = 50)
    private String describe;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 1)
    private StatusEnum status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "operator", length = 20)
    private String operator;

    @Column(name = "operate_time")
    private Date operateTime;

    @Column(name = "operate_ip", length = 20)
    private String operateIp;

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateIp() {
		return operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

}
