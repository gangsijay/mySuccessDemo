package com.two.oauthserver.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.two.core.base.jdbc.dao.impl.PostgreJdbcDao;
import com.two.core.exception.DaoException;
import com.two.oauthserver.security.dao.SysUserDAO;
import com.two.oauthserver.security.dao.entity.SysUserDO;

/**
 * 功能描述: ${description}
 *
 * @author: 
 * @create: 2018-05-10 17:05
 **/

@Repository
public class SysUserDAOImpl extends PostgreJdbcDao implements SysUserDAO {

    @Override
    public SysUserDO getSysUserById(Long id) throws DaoException {
        StringBuilder sql = new StringBuilder(50);
        sql
                .append(" SELECT * FROM sys_user u WHERE u.id = ")
                .append(id);
        return this.queryForObject(sql.toString(), SysUserDO.class);
    }

    @Override
    public SysUserDO getSysUserByCode(String code) throws DaoException {
        StringBuilder sql = new StringBuilder(50);
        sql.append(" SELECT * FROM sys_user u WHERE u.code =  ?");
        return this.queryForObject(sql.toString(), SysUserDO.class, code);
    }

    @Override
    public SysUserDO getSysUserByLoginName(String name) throws DaoException {
        StringBuilder sql = new StringBuilder(50);
        sql
                .append("SELECT * FROM sys_user u WHERE u.Login_name = ? ");
        return this.queryForObject(sql.toString(), SysUserDO.class, name);
    }



}

