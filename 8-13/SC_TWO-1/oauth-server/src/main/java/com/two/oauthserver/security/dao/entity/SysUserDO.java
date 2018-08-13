package com.two.oauthserver.security.dao.entity;

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
 * 功能描述: ${description}
 *
 * @author: 田杰
 * @create: 2018-05-09 15:45
 **/
@Entity
@Table(name = "sys_user")
public class SysUserDO extends GlobalEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name",length = 20)
    private String name;

    @Column(name = "code",unique = true,length = 30)
    private String code;
    /***
     *登陆名称-登陆唯一编号
     */
    @Column(name = "login_name",unique = true,length = 20)
    private String loginName;

    @Column(name = "en_name",length = 20)
    private String enName;

    @Column(name = "password",length = 40)
    private String password;


    @Column(name = "tel_phone",length = 20)
    private String telPhone;

    @Column(name = "mobile_phone",length = 20)
    private String mobilePhone;

    @Column(name = "address",length = 100)
    private String address;

    @Column(name = "email",length = 20)
    private String email;

    @Column(name = "remark",length = 150)
    private String remark;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",length = 1)
    private StatusEnum status;

    @Column(name = "dept_id",length = 21)
    private String deptId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "operator",length = 20)
    private String operator;

    @Column(name = "operate_time")
    private Date operateTime;

    @Column(name = "operate_ip",length = 20)
    private String operateIp;

    @Transient
    private List<SysRoleDO> roleList ;

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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

	public List<SysRoleDO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRoleDO> roleList) {
		this.roleList = roleList;
	}
}
