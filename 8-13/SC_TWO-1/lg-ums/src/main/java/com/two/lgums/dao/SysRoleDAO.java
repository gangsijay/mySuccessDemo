package com.two.lgums.dao;

import java.util.List;

import com.two.core.exception.DaoException;
import com.two.lgums.dao.entity.SysRoleDO;

/**
 * 功能描述: ${description}
 *
 * @author: 田杰
 * @create: 2018-05-15 10:45
 **/
public interface SysRoleDAO {

    /**
     * 功能描述: 根据角色编号查询拥有角色
     *
     * @param: [id]
     * @return: java.util.List<com.cdrundle.auth.dao.entity.SysRoleDO>
     * @auther: 田杰
     * @date: 2018/5/28
     */
    List<SysRoleDO> getRoleByUserId(Long id) throws DaoException;

//    /**
//     * 功能描述:根据查询条件查询拥有角色
//     *
//     * @param: [roleQuery]
//     * @return: java.util.List<com.cdrundle.auth.dao.entity.SysRoleDO>
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    List<SysRoleDO> findListRole(SysRoleQuery roleQuery) throws DaoException;
//
//    /**
//     * 功能描述: 分页查询拥有角色
//     *
//     * @param: [pageQuery]
//     * @return: com.cdrundle.common.util.Page<com.cdrundle.auth.dao.entity.SysRoleDO>
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    Page<SysRoleDO> findByPage(SysRoleQuery pageQuery) throws DaoException;
//
//    /**
//     * 功能描述: 保存角色信息
//     *
//     * @param: [sysRoleDO]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    void  save(SysRoleDO sysRoleDO) throws DaoException;
//    /**
//     * 功能描述:更新角色信息
//     *
//     * @param: [roleQuery]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    void  update(SysRoleDO sysRoleDO) throws DaoException;
//
//    /**
//     * 功能描述: 删除角色
//     *
//     * @param: [id]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    void deleteById(Long id) throws DaoException;
//    
//    
//    
//    
////    List<SysRoleDO> getRoleByUserId(Long id) throws DaoException;
//    
//    /**
//     * 根据机构查询角色
//     * lwq
//     * @param id
//     * @return
//     * @throws DaoException
//     */
//    List<SysRoleDO> findRoleByOrgId(Long id) throws DaoException;
//    
//    int saveRole(SysRoleDO role) throws DaoException;
//    int deleteRoleById(long id) throws DaoException;
//    int updateRoleById(long id) throws DaoException;
//    
//    /**
//     * 根据用户删除角色
//     * lwq
//     * @param id
//     * @return
//     * @throws DaoException
//     */
//    int deleteUserRoleByUser(long id)throws DaoException;
//    /**
//     * 新增用户角色
//     * lwq
//     * @param roleId
//     * @param userId
//     * @return
//     * @throws DaoException
//     */
//    int saveUserRole(String[] roleId,long userId) throws DaoException;
//    /**
//     * 查询用户角色
//     * lwq
//     * @param id
//     * @return
//     * @throws DaoException
//     */
//    List<SysUserRoleDO> getUserRoles(long id)throws DaoException;
//
//    /**
//     * 查询用户拥有角色
//     * lwq
//     * @param id
//     * @return
//     * @throws DaoException
//     */
//    List<SysRoleDO> getUserHaveRole(Long id)throws DaoException;
//    /**
//     * 查询用户为拥有角色
//     * lwq
//     * @param id
//     * @param pageQuery
//     * @return
//     * @throws DaoException
//     */
//    Page<SysRoleDO> getUserNotHaveRole(Long id,SysRoleQuery sysRoleQuery)throws DaoException;
}
