package com.two.oauthserver.security.dao;

import com.two.core.exception.DaoException;
import com.two.oauthserver.security.dao.entity.SysUserDO;

/**
 * 功能描述: ${description}
 *
 **/
public interface SysUserDAO {
	
    SysUserDO getSysUserById(Long id) throws DaoException;
    
    SysUserDO getSysUserByCode(String code) throws DaoException;

    SysUserDO getSysUserByLoginName(String name) throws DaoException;

}
