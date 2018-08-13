package com.two.lgums.dao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.two.core.base.common.enums.StatusEnum;
import com.two.core.base.jdbc.entity.GlobalEntity;

/**
 * 功能描述: 权限表
 *
 * @author: 田杰
 * @create: 2018-05-10 10:22
 **/
@Entity
@Table(name = "sys_lg_permission")
public class SysPermissionDO extends GlobalEntity {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "code",length = 30)
    private String code;

    @Column(name = "platformId",length = 30)
    private String platformId;

    @Column(name = "name",length = 30)
    private String name;

    @Column(name = "uri",length = 50)
    private String uri;

    @Column(name = "pid")
    private Long pid;

    /**** 
     * 排序编号
     */
    @Column(name = "order_num")
    private Integer orderNum;

    @Column(name = "icon",length = 20)
    private String icon;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",length = 1)
    private StatusEnum status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "operator",length = 20)
    private String operator;

    @Column(name = "operate_time")
    private Date operateTime;

    @Column(name = "operate_ip",length = 20)
    private String operateIp;
    
    @Transient
    private List<SysPermissionDO> child;

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

	public List<SysPermissionDO> getChild() {
		return child;
	}

	public void setChild(List<SysPermissionDO> child) {
		this.child = child;
	}
}
