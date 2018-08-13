package com.two.lgums.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.two.core.base.common.security.SecurityConstants;
import com.two.lgums.service.bo.SysRoleBO;
import com.two.lgums.service.bo.SysUserBO;

/**
 * 功能描述: 用户
 *
 * @author: 田杰
 * @create: 2018-05-14 10:33
 **/
public class ArtAuthUser  implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
    private String password;
    private List<SysRoleBO> roleList;

    public ArtAuthUser() {

    }
    
    public ArtAuthUser(SysUserBO bo) {
        this.username = bo.getLoginName();
        this.password = bo.getPassword();
        this.roleList = bo.getRoleList();
    }

    public ArtAuthUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//        roleList.stream().forEach(role-> authorityList.add(new SimpleGrantedAuthority(StringUtils.upperCase(role.getCode()))));
//        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
//        return authorityList;
//    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorityList = new ArrayList<>();
        roleList.stream().forEach(role-> authorityList.add(new SimpleGrantedAuthority(StringUtils.upperCase(role.getCode()))));
        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
        return authorityList;
	}

	public List<SysRoleBO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRoleBO> roleList) {
		this.roleList = roleList;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
