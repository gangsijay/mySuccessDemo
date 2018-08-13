package com.two.oauthserver.security.config;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.two.core.base.common.enums.BizErrorCode;
import com.two.core.exception.BizException;
import com.two.core.exception.DaoException;
import com.two.oauthserver.security.dao.SysRoleDAO;
import com.two.oauthserver.security.dao.SysUserDAO;
import com.two.oauthserver.security.dao.entity.SysRoleDO;
import com.two.oauthserver.security.dao.entity.SysUserDO;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
    SysUserDAO userDAO;

    @Autowired
    SysRoleDAO roleDAO;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		//log.info("用户:[{}]开始登陆认证", username) ;
	    SysUserDO user = null;
	    try {
	        user= userDAO.getSysUserByLoginName(username);
	    } catch (DaoException e) {
	        //log.error("oauth用户登陆查询失败", e);
	        throw new UsernameNotFoundException("用户登陆失败", e);
	    }
	    if (Objects.isNull(user)) {
	        throw new UsernameNotFoundException(BizErrorCode.BIZ_AUTH_NO_USER.describe,
	                BizException.wrap(BizErrorCode.BIZ_AUTH_NO_USER));
	    }
	    List<SysRoleDO> roles = null;
	    try {
	        roles = roleDAO.getRoleByUserId(user.getId());
	    } catch (DaoException e) {
	        BizException.wrap(BizErrorCode.BIZ_AUTH_NO_USER);
	    }
	    user.setRoleList(roles);
//	    passwordEncoder.setSalt(username);
	    return new ArtAuthUser(user);
    }
	
	private User mockUser() {
		Collection<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("admin"));//用户所拥有的角色信息
		User user = new User("admin",new BCryptPasswordEncoder().encode("123456"), authorities);
		System.out.println("123456:"+new BCryptPasswordEncoder().encode("123456"));
		return user;
	}
}