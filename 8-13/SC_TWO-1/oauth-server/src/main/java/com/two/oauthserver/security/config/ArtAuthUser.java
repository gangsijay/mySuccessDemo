package com.two.oauthserver.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.two.core.base.common.enums.StatusEnum;
import com.two.core.base.common.security.SecurityConstants;
import com.two.oauthserver.security.dao.entity.SysRoleDO;
import com.two.oauthserver.security.dao.entity.SysUserDO;


/**
 * 功能描述: 用户
 *
 * @author: 田杰
 * @create: 2018-05-14 10:33
 **/
public class ArtAuthUser implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String loginName;
    private String username;
    private String password;
    private StatusEnum status;
    private List<SysRoleDO> roleList;
    public ArtAuthUser() {

    }
    public ArtAuthUser(SysUserDO sysUser) {
        this.id=sysUser.getId();
        this.loginName=sysUser.getLoginName();
        this.username = sysUser.getLoginName();
        this.password = sysUser.getPassword();
        this.status = sysUser.getStatus();
        this.roleList = sysUser.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roleList.stream().forEach(role-> authorityList.add(new SimpleGrantedAuthority(StringUtils.upperCase(role.getCode()))));
        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return StatusEnum.N.equals(status) ? false : true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public List<SysRoleDO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<SysRoleDO> roleList) {
		this.roleList = roleList;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
