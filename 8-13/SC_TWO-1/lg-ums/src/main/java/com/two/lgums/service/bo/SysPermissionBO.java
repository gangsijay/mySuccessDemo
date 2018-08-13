package com.two.lgums.service.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.two.core.base.common.enums.StatusEnum;

/**
 * 功能描述: 权限菜单
 *
 * @author: 田杰
 * @create: 2018/5/22 10:38
 **/
public class SysPermissionBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;

    private String code;

    private String platformId;

    private String name;

    private String uri;

    private Long pid;

    /****
     * 排序编号
     */
    private Integer orderNum;

    private String icon;

    private StatusEnum status;

    private Date createTime;

    private String operator;

    private Date operateTime;

    private String operateIp;

    List<SysPermissionBO> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public List<SysPermissionBO> getChildren() {
		return children;
	}

	public void setChildren(List<SysPermissionBO> children) {
		this.children = children;
	}
}
