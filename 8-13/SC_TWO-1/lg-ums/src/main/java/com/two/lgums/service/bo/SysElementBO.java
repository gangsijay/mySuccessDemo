package com.two.lgums.service.bo;

import java.io.Serializable;
import java.util.Date;

import com.two.core.base.common.enums.StatusEnum;

/**
 * 功能描述: 元素表
 *
 * @author: 田杰
 * @create: 2018-05-10 11:56
 **/
public class SysElementBO implements Serializable {
    private static final long serialVersionUID = -3968109163544228245L;

    private Long Id;

    private Long sysPermissionId;

    private String name;

    private String code;

    private String uri;

    private String method;

    private StatusEnum status;

    private Date createTime;

    private String operator;

    private Date operateTime;

    private String operateIp;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getSysPermissionId() {
		return sysPermissionId;
	}

	public void setSysPermissionId(Long sysPermissionId) {
		this.sysPermissionId = sysPermissionId;
	}

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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
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
