package com.two.oauthserver.security.config;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.two.core.base.common.enums.BizErrorCode;
import com.two.core.exception.BizException;
import com.two.core.exception.DaoException;
import com.two.oauthserver.security.dao.SysRoleDAO;
import com.two.oauthserver.security.dao.SysUserDAO;
import com.two.oauthserver.security.dao.entity.SysRoleDO;
import com.two.oauthserver.security.dao.entity.SysUserDO;


/**
 * Created by Tjee on 2018/4/4.
 */
@Component
public class ArtUserDetailsService implements UserDetailsService {

    @Autowired
    SysUserDAO userDAO;

    @Autowired
    SysRoleDAO roleDAO;
    
    Logger log = LoggerFactory.getLogger(ArtUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户:[{}]开始登陆认证", username) ;
        SysUserDO user = null;
        try {
            user= userDAO.getSysUserByLoginName(username);
        } catch (DaoException e) {
            log.error("oauth用户登陆查询失败", e);
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
//        passwordEncoder.setSalt(username);
        return new ArtAuthUser(user);
    }
}
