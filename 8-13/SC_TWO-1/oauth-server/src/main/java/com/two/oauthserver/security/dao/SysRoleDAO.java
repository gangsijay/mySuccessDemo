package com.two.oauthserver.security.dao;

import java.util.List;

import com.two.core.exception.DaoException;
import com.two.oauthserver.security.dao.entity.SysRoleDO;

/**
 * 功能描述: ${description}
 *
 **/
public interface SysRoleDAO {
	
	public List<SysRoleDO> getRoleByUserId(Long id) throws DaoException;

}
