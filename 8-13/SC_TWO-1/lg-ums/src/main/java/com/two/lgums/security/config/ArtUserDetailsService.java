package com.two.lgums.security.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.two.core.base.common.enums.BizErrorCode;
import com.two.core.exception.BizException;
import com.two.lgums.service.SysRoleService;
import com.two.lgums.service.SysUserService;
import com.two.lgums.service.bo.SysRoleBO;
import com.two.lgums.service.bo.SysUserBO;

/**
 * Created by Tjee on 2018/4/4.
 */
@Component
public class ArtUserDetailsService implements UserDetailsService {

	@Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("用户:[{}]开始登陆认证", username);
        SysUserBO userBO = null;
        try {
            userBO = sysUserService.login(username);
        } catch (BizException e) {
//            log.error("用户登陆查询失败", e);
            throw new UsernameNotFoundException("用户登陆失败", e);
        }
        if (Objects.isNull(userBO)) {
            throw new UsernameNotFoundException(BizErrorCode.BIZ_AUTH_NO_USER.describe,
                    BizException.wrap(BizErrorCode.BIZ_AUTH_NO_USER));
        }
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        List<SysRoleBO> roles = sysRoleService.getRoleByUserId(userBO.getId());
        userBO.setRoleList(roles);
        userBO.setPassword(new BCryptPasswordEncoder().encode(userBO.getPassword()));//临时加密
        //passwordEncoder.setSalt(username);
        return new ArtAuthUser(userBO);
    }
}
