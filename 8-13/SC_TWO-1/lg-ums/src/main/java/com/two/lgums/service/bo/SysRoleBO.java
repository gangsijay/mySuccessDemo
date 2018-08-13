package com.two.lgums.service.bo;

import java.io.Serializable;
import java.util.Date;

import com.two.core.base.common.enums.StatusEnum;

/**
 * 功能描述: 系统角色表
 *
 * @author: 田杰
 * @create: 2018-05-10 09:35
 **/

public class SysRoleBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;

    private Integer level;

    private String code;

    private String name;

    private String describe;

    private StatusEnum status;

    private Date createTime;

    private String operator;

    private Date operateTime;

    private String operateIp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
