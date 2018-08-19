package com.two.secuoauth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.two.core.base.jdbc.dao.impl.PostgreJdbcDao;
import com.two.core.exception.DaoException;
import com.two.secuoauth.dao.SysPermissionDao;
import com.two.secuoauth.pojo.SysPermission;

/**
 * 功能描述: ${description}
 *
 * @author: 
 * @create: 2018-05-10 17:05
 **/

@Repository
public class SysPermissionDaoImpl extends PostgreJdbcDao implements SysPermissionDao {

	@Override
	public List<SysPermission> findAll()  {
		StringBuilder sql = new StringBuilder(50);
        sql.append(" SELECT * from lg_permission ");
        List<SysPermission> list=null;
        try{
        	list=this.queryForList(sql.toString(), SysPermission.class);
		}catch(DaoException e){
			e.printStackTrace();
	    }
        return list;
	}

	@Override
	public List<SysPermission> findByAdminUserId(Long userId)  {
		StringBuilder sql = new StringBuilder(50);
        sql.append(" SELECT ");
		sql.append(" p.* ");
		sql.append(" FROM lg_user u ");
		sql.append(" LEFT JOIN lg_user_role sru ON u.id= sru.sys_user_id ");
		sql.append(" LEFT JOIN lg_role r ON sru.sys_role_id=r.id ");
		sql.append(" LEFT JOIN lg_role_permission spr ON spr.sys_role_id=r.id ");
		sql.append(" LEFT JOIN lg_permission p ON p.id =spr.sys_permission_id ");
		sql.append(" WHERE u.id=? ");
        List<SysPermission> list=null;
        try {
			list=this.queryForList(sql.toString(), SysPermission.class, userId);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return list;
	}



}

