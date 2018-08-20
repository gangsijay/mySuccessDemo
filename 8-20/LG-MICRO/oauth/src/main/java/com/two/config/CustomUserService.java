package com.two.config;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.two.dao.SysPermissionDao;
import com.two.dao.SysUserDao;
import com.two.pojo.SysPermission;
import com.two.pojo.SysUser;
 
/**
 * 自定义UserDetailsService,将用户权限交给springsecurity进行管控
 *
 * @Author: 我爱大金子
 * @Description: 将用户权限交给Springsecurity进行管控
 * @Date: Create in 16:19 2017/7/5
 */
@Component
public class CustomUserService implements UserDetailsService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = sysUserDao.findByUserName(username);
        if (user != null) {
            List<SysPermission> permissions = sysPermissionDao.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            for (SysPermission permission : permissions) {
                if (permission != null && permission.getName()!=null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
//            return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), grantedAuthorities);
            return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}