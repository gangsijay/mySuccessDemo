package com.two.lgums.service;

import java.util.List;

import com.two.core.exception.BizException;
import com.two.lgums.dao.entity.SysRoleDO;
import com.two.lgums.service.bo.SysRoleBO;

/**
 * 功能描述: 系统用户业务接口
 *
 * @author: 田杰
 * @create: 2018-05-11 10:29
 **/
public interface SysRoleService {


    /*** 
     * @功能描述: 获取用户角色
     * @Param: [id]
     * @return: java.util.List<com.cdrundle.auth.dao.entity.SysRoleDO> 
     * @Author: 田杰
     * @Date: 2018/5/15
     */
    List<SysRoleBO> getRoleByUserId(Long id) throws BizException;

    /**
     * 功能描述:根据查询条件查询拥有角色
     *
     * @param: [roleQuery]
     * @return: java.util.List<com.cdrundle.auth.dao.entity.SysRoleDO>
     * @auther: 田杰
     * @date: 2018/5/28
     */
//    List<SysRoleBO> findListRole(SysRoleQuery roleQuery) throws BizException;

    /**
     * 功能描述: 分页查询拥有角色
     *
     * @param: [pageQuery]
     * @return: com.cdrundle.common.util.Page<com.cdrundle.auth.dao.entity.SysRoleDO>
     * @auther: 田杰
     * @date: 2018/5/28
     */
//    Page<SysRoleBO> findByPage(SysRoleQuery pageQuery) throws BizException;

    /**
     * 功能描述: 保存角色信息
     *
     * @param: [sysRoleDO]
     * @return: void
     * @auther: 田杰
     * @date: 2018/5/28
     */
//    void  save(SysRoleBO sysRoleBO) throws BizException;
//    /**
//     * 功能描述:更新角色信息
//     *
//     * @param: [roleQuery]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    void  update(SysRoleBO roleQuery) throws BizException;
//
//    /**
//     * 功能描述: 删除角色
//     *
//     * @param: [id]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    void deleteById(Long id) throws BizException;
//    
//    
//    /**
//     * 功能描述: 根据机构查询角色
//     *
//     * @auther: lwq
//     * @date: 2018/5/31
//     */
//    List<SysRoleBO> findRoleByOrgId(Long id) throws BizException;       
//    int saveRole(SysRoleDO role) throws BizException;
//    int deleteRoleById(long id) throws BizException;
//    int updateRoleById(long id) throws BizException;
//    /**
//     * 功能描述: 查询用户角色
//     *
//     * @auther: lwq
//     * @date: 2018/5/31
//     */
//    List<Long> getUserRoles(long id)throws BizException;
//    
//    /**
//     * 功能描述: 查询用户拥有角色
//     *
//     * @return: void
//     * @auther: lwq
//     * @date: 2018/5/31
//     */
//    List<SysRoleBO> getUserHaveRole(Long id)throws BizException;
//    /**
//     * 功能描述: 查询用户未拥有角色
//     *
//     * @auther: lwq
//     * @date: 2018/5/31
//     */
//    Page<SysRoleBO> getUserNotHaveRole(Long id,SysRoleQuery sysRoleQuery)throws BizException;
    
}
