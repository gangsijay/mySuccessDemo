package com.two.lgums.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.two.core.base.jdbc.dao.impl.PostgreJdbcDao;
import com.two.core.exception.DaoException;
import com.two.lgums.dao.SysRoleDAO;
import com.two.lgums.dao.entity.SysRoleDO;

/**
 * 功能描述: 根据角色编号查询拥有角色 默认查询状态为启用
 *
 * @author: 田杰
 * @create: 2018-05-15 10:49
 **/
@Repository
public class SysRoleDAOImpl extends PostgreJdbcDao implements SysRoleDAO {
    @Override
    public List<SysRoleDO> getRoleByUserId(Long id) throws DaoException {
        StringBuilder sql = new StringBuilder(50);
        sql
                .append(" SELECT SR.id, SR.code,SR.name ")
                .append(" FROM sys_lg_user U, sys_user_role UR ,sys_lg_role SR ")
                .append(" WHERE U.id=UR.sys_user_id AND UR.sys_role_id=SR.id  ")
                .append(" AND u.id= ? ");
        List<SysRoleDO> list=null;
        try {
            list=this.queryForList(sql.toString(), SysRoleDO.class, id);
        }catch(Exception e){
            throw DaoException.wrap(e);
        }
        return  list ;
    }

//    /**
//     * 功能描述:根据查询条件查询拥有角色
//     *
//     * @param: [roleQuery]
//     * @return: java.util.List<com.cdrundle.auth.dao.entity.SysRoleDO>
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    @Override
//    public List<SysRoleDO> findListRole(SysRoleQuery pageQuery) throws DaoException {
//
//        StringBuilder sql = new StringBuilder(50);
//        sql
//                .append(" SELECT ")
//                .append(" id,code,name,status,level, create_time \"createTime\",operator,operate_ip \"operateIp\",operate_time \"operateTime\",describe ")
//                .append(" FROM sys_role R WHERE 1=1 ");
//        List<Object> args=new ArrayList<>();
//        if (Objects.nonNull(pageQuery.getId())) {
//            sql.append(" AND R.id = ? ");
//            args.add(pageQuery.getId());
//        }
//        if (StringUtils.isNotBlank(pageQuery.getName())) {
//            sql.append(" AND R.name like ? ");
//            args.add("%"+pageQuery.getName().trim()+"%");
//        }
//        if (Objects.nonNull(pageQuery.getStatus()) &&
//                StringUtils.isNotBlank(pageQuery.getStatus().name())) {
//            sql.append(" AND R.status = ? ");
//            args.add(pageQuery.getStatus().name());
//        }
//        if (Objects.nonNull(pageQuery.getLevel())) {
//            sql.append(" AND R.level = ? ");
//            args.add(pageQuery.getLevel());
//        }
//        if (StringUtils.isNotBlank(pageQuery.getOperator())) {
//            sql.append(" AND R.operator like ?");
//            args.add("%"+pageQuery.getOperator().trim()+"%");
//        }
//        if (StringUtils.isNotBlank(pageQuery.getOperateIp())) {
//            sql.append(" AND R.operate_ip = ? ");
//            args.add(pageQuery.getOperateIp().trim());
//        }
//        if (StringUtils.isNotBlank(pageQuery.getCode())){
//            sql.append(" AND R.code like ? ");
//            args.add("%"+pageQuery.getCode().trim()+"%");
//        }
//        sql.append(" ORDER BY create_time DESC");
//        List<SysRoleDO> list=Collections.EMPTY_LIST;
//        try {
//           list = this.queryForList(sql.toString(),args.toArray(),SysRoleDO.class);
//        }catch(Exception e){
//            throw DaoException.wrap(e);
//        }
//        return  list;
//    }
//
//    /**
//     * 功能描述: 分页查询拥有角色
//     *
//     * @param: [pageQuery]
//     * @return: com.cdrundle.common.util.Page<com.cdrundle.auth.dao.entity.SysRoleDO>
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    @Override
//    public Page<SysRoleDO> findByPage(SysRoleQuery pageQuery) throws DaoException {
//        StringBuilder sql = new StringBuilder(50);
//        sql
//                .append(" SELECT ")
//                .append(" id,code,name,status,level, create_time \"createTime\",operator,operate_ip \"operateIp\",operate_time \"operateTime\",describe ")
//                .append(" FROM sys_role R WHERE 1=1 ");
//        List<Object> args=new ArrayList<>();
//        if (Objects.nonNull(pageQuery.getId())) {
//            sql.append(" AND R.id = ? ");
//            args.add(pageQuery.getId());
//        }
//        if (StringUtils.isNotBlank(pageQuery.getName())) {
//            sql.append(" AND R.name like ? ");
//            args.add("%"+pageQuery.getName().trim()+"%");
//        }
//        if (Objects.nonNull(pageQuery.getStatus()) &&
//                StringUtils.isNotBlank(pageQuery.getStatus().name())) {
//            sql.append(" AND R.status = ? ");
//            args.add(pageQuery.getStatus().name());
//        }
//        if (Objects.nonNull(pageQuery.getLevel())) {
//            sql.append(" AND R.level = ? ");
//            args.add(pageQuery.getLevel());
//        }
//        if (StringUtils.isNotBlank(pageQuery.getOperator())) {
//            sql.append(" AND R.operator like ?");
//            args.add("%"+pageQuery.getOperator().trim()+"%");
//        }
//        if (StringUtils.isNotBlank(pageQuery.getOperateIp())) {
//            sql.append(" AND R.operate_ip = ? ");
//            args.add(pageQuery.getOperateIp().trim());
//        }
//        if (StringUtils.isNotBlank(pageQuery.getCode())){
//            sql.append(" AND R.code like ? ");
//            args.add("%"+pageQuery.getCode().trim()+"%");
//        }
//        sql.append(" ORDER BY create_time DESC");
//        Page<SysRoleDO> sysRoleDOPage =null;
//        try {
//            sysRoleDOPage = this.queryByPage(sql.toString(), pageQuery, args.toArray(), SysRoleDO.class);
//        }catch(Exception e){
//            throw DaoException.wrap(e);
//        }
//        return  sysRoleDOPage;
//    }
//
//    /**
//     * 功能描述: 保存角色信息
//     *
//     * @param: [sysRoleDO]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    @Override
//    public void save(SysRoleDO role) throws DaoException {
//        StringBuilder sql = new StringBuilder(100);
//        sql
//                .append(" INSERT INTO sys_role ")
//                .append(" (id,code,name,status,level,create_time,operator,operate_ip ,operate_time ,describe) ")
//                .append(" VALUES(?,?,?,?,?,?,?,?,?,?)");
//
//        Object[] params = new Object[] {
//                idWorker.nextId(),role.getCode(),
//                role.getName(),Objects.nonNull(role.getStatus())?role.getStatus().name():StatusEnum.N.name(),
//                role.getLevel(),new Date(),
//                role.getOperator(),role.getOperateIp(),
//                new Date(),role.getDescribe()
//        };
//        int[] types = new int[] {
//                Types.BIGINT,Types.VARCHAR,
//                Types.VARCHAR,Types.VARCHAR,
//                Types.INTEGER,Types.DATE,
//                Types.VARCHAR, Types.VARCHAR,
//                Types.DATE,Types.VARCHAR
//        };
//        try {
//            this.update(sql.toString(),params,types);
//        }catch (Exception e){
//            throw  DaoException.wrap(e);
//        }
//
//    }
//
//    /**
//     * 功能描述:更新角色信息
//     *
//     * @param: [roleQuery]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    @Override
//    public void update(SysRoleDO role) throws DaoException {
//        StringBuilder sql = new StringBuilder(100);
//        sql
//                .append(" UPDATE  sys_role ")
//                .append(" SET code=?,name=?,status=?,level=?,operator=?,operate_ip=? ,operate_time =?,describe=? ")
//                .append(" WHERE id=?");
//
//        Object[] params = new Object[]{
//                role.getCode(),role.getName(),
//                role.getStatus().name(),role.getLevel(),
//                role.getOperator(),role.getOperateIp(),
//                new Date(),role.getDescribe(),
//                role.getId()
//
//        };
//        int[] types = new int[]{
//                Types.VARCHAR, Types.VARCHAR,
//                Types.VARCHAR, Types.INTEGER,
//                Types.VARCHAR, Types.VARCHAR,
//                Types.DATE, Types.VARCHAR,
//                Types.BIGINT
//        };
//        try {
//            this.update(sql.toString(), params, types);
//        } catch (Exception e) {
//            throw DaoException.wrap(e);
//        }
//    }
//
//    /**
//     * 功能描述: 删除角色
//     *
//     * @param: [id]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    @Override
//    public void deleteById(Long id) throws DaoException {
//        StringBuilder sql = new StringBuilder(50);
//        sql.append(" DELETE  FROM sys_role R WHERE R.id = ?");
//        try {
//            this.update(sql.toString(), id);
//        } catch (Exception e) {
//            throw DaoException.wrap(e);
//        }
//    }
//    
//    
//    
//    
//    @Override
//	public List<SysRoleDO> findRoleByOrgId(Long id) throws DaoException {
//		 StringBuilder sql = new StringBuilder(50);
//	        sql
////	                .append(" SELECT id,code,name,create_time,describe,operate_ip,operate_time,operator,status ")
////	                .append(" FROM sys_role where organization=? ");
//	        .append(" SELECT id,code,name,create_time,describe,operate_ip,operate_time,operator,status ")
//	        .append(" FROM sys_role WHERE status='Y' ");
//	        return  this.queryForList(sql.toString(), SysRoleDO.class);
//	}
//
//	@Override
//	public int saveRole(SysRoleDO role) throws DaoException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int deleteRoleById(long id) throws DaoException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateRoleById(long id) throws DaoException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int deleteUserRoleByUser(long id) throws DaoException {
//		StringBuilder sql=new StringBuilder();
//		sql.append(" DELETE FROM sys_user_role WHERE sys_user_id="+id+" ");
//		return update(sql.toString());
//	}
//
//	@Override
//	public int saveUserRole(String[] roleId, long userId) throws DaoException {
//		StringBuilder sql=new StringBuilder();
//		sql.append(" INSERT INTO sys_user_role(id,sys_role_id,sys_user_id) ");
//		sql.append(" VALUES ");
//		for(String id:roleId) {
//			sql.append(" ("+idWorker.nextId()+","+id+","+userId+"), ");
//		}	
//		return update(sql.deleteCharAt(sql.lastIndexOf(",")).toString());
//	}
//
//	@Override
//	public List<SysUserRoleDO> getUserRoles(long id) throws DaoException {
//		StringBuilder sql=new StringBuilder();
//		sql.append(" SELECT sys_role_id from sys_user_role WHERE sys_user_id=? ");
//		List<SysUserRoleDO> list=this.queryForList(sql.toString(), SysUserRoleDO.class, id);
//		List<Long> roleIds=new ArrayList<>();
//		for(SysUserRoleDO l:list) {
//			roleIds.add(l.getSysRoleId());
//		}
//		return this.queryForList(sql.toString(), SysUserRoleDO.class, id);
//	}
//
//	@Override
//	public List<SysRoleDO> getUserHaveRole(Long id) throws DaoException {
//		StringBuilder sql=new StringBuilder();
//		sql.append(" SELECT r.id,r.name,r.code,r.describe FROM  sys_role r ")
//		.append(" JOIN sys_user_role ur ON r.id=ur.sys_role_id where ur.sys_user_id=? AND r.status='Y' ORDER BY r.id ");
//		return this.queryForList(sql.toString(),SysRoleDO.class,id);
//	}
//
//	@Override
//	public Page<SysRoleDO> getUserNotHaveRole(Long id,SysRoleQuery sysRoleQuery) throws DaoException {
//		StringBuilder sql=new StringBuilder();
//		sql.append(" SELECT r.id,r.name,r.code,r.describe FROM  sys_role r ")
//		.append(" WHERE r.status='Y' AND  r.id NOT IN( SELECT ur.sys_role_id FROM sys_user_role ur WHERE  ur.sys_user_id=? )   ");
//		if(!StringUtils.isEmpty(sysRoleQuery.getName())) {
//			sql.append(" AND r.name like '"+sysRoleQuery.getName()+"%' ");
//		}
//		if(!StringUtils.isEmpty(sysRoleQuery.getCode())) {
//			sql.append(" AND r.code like '"+sysRoleQuery.getCode()+"%' ");
//		}
//		sql.append(" ORDER BY r.id ");
//		return queryByPage(sql.toString(), sysRoleQuery, SysRoleDO.class,id);
//	}
}
