package com.two.dao.impl;

import org.springframework.stereotype.Repository;

import com.two.core.base.jdbc.dao.impl.PostgreJdbcDao;
import com.two.core.exception.DaoException;
import com.two.dao.SysUserDao;
import com.two.pojo.SysUser;

/**
 * 功能描述: ${description}
 *
 * @author: 
 * @create: 2018-05-10 17:05
 **/

@Repository
public class SysUserDaoImpl extends PostgreJdbcDao implements SysUserDao {

	@Override
	public SysUser findByUserName(String username) {
		StringBuilder sql = new StringBuilder(50);
        sql.append(" select u.* ");
		sql.append(" from lg_user u ");
		sql.append(" LEFT JOIN lg_user_role sru on u.id= sru.sys_user_id ");
		sql.append(" LEFT JOIN lg_role r on sru.sys_role_id=r.id ");
		sql.append(" where u.username= ? ");
        try {
			return this.queryForObject(sql.toString(), SysUser.class, username);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}



}

