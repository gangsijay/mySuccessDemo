package com.two.oauthserver.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.two.core.base.jdbc.dao.impl.PostgreJdbcDao;
import com.two.core.exception.DaoException;
import com.two.oauthserver.security.dao.SysRoleDAO;
import com.two.oauthserver.security.dao.entity.SysRoleDO;

/**
 * 功能描述: ${description}
 *
 * @author: 
 * @create: 2018-05-10 17:05
 **/

@Repository
public class SysRoleDAOImpl extends PostgreJdbcDao implements SysRoleDAO {
	@Override
    public List<SysRoleDO> getRoleByUserId(Long id) throws DaoException {
        StringBuilder sql = new StringBuilder(50);
        sql
                .append(" SELECT SR.id, SR.code,SR.name  ")
                .append(" FROM sys_user U, sys_user_role UR ,sys_role SR ")
                .append(" WHERE U.id=UR.sys_user_id AND UR.sys_role_id=SR.id AND  U.status='Y' AND SR.status='Y' ")
                .append(" AND u.id= ? ");
        List<SysRoleDO> list=null;
        try {
            list=this.queryForList(sql.toString(), SysRoleDO.class, id);
        }catch(Exception e){
            throw DaoException.wrap(e);
        }
        return  list ;
    }

}

