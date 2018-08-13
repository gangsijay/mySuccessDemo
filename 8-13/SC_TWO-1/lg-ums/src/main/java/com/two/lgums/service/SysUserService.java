package com.two.lgums.service;

import java.util.List;

import com.two.core.base.common.util.Page;
import com.two.core.base.common.util.PageQuery;
import com.two.core.exception.BizException;
import com.two.lgums.dao.entity.SysUserDO;
import com.two.lgums.service.bo.SysUserBO;

/**
 * 功能描述: 系统用户业务接口
 *
 * @author: lwq
 * @create: 2018-05-11 10:29
 **/
public interface SysUserService {



    public SysUserBO login(String name) throws BizException;
    
//    /**
//     * 根据部门查询用户
//     * @param id
//     * @param p
//     * @param isEnable
//     * @return
//     * @throws BizException
//     */
//    public Page<SysUserDO> findByDept(long id,	PageQuery p,boolean isEnable) throws BizException;
//    /**
//     * 根据机构查询用户
//     * @param idlong
//     * @param p
//     * @param isEnable
//     * @return
//     * @throws BizException
//     */
//    public Page<SysUserDO> findByOrg(long idlong ,	PageQuery p,boolean isEnable) throws BizException;
//    /**
//     * 新增用户
//     * @param user
//     * @param roleIds
//     * @return
//     * @throws BizException
//     */
//    public long saveUser(SysUserDO user,String roleIds) throws BizException;
//    /**
//     * 根据ID删除用户
//     * @param id
//     * @return
//     * @throws BizException
//     */
//    int deleteUserById(long id) throws BizException;
//    /**
//     * 根据ID修改用户
//     * @param user
//     * @param roleIds
//     * @return
//     * @throws BizException
//     */
//    int updateUserById(SysUserDO user,String roleIds) throws BizException;
//    /**
//     * 查询登录名是否重复
//     * @param text
//     * @return
//     * @throws BizException
//     */
//    int checkLoginName(String text) throws BizException;
//    /**
//     * 查询登录名是否重复(修改用户时)
//     * @param text
//     * @param id
//     * @return
//     * @throws BizException
//     */
//    int checkUpdateLoginName(String text,long id) throws BizException;
//    /**
//     * 查询上级医生
//     * @param id
//     * @return
//     * @throws BizException
//     */
//    List<SysUserDO> getParentDocotrsById(long id)throws BizException;
//    /**
//     * 根据登录名查询用户
//     * @param userName
//     * @return
//     * @throws BizException
//     */
//    SysUserDO findByUserName(String userName)throws BizException;
//    /**
//     * 保存用户角色
//     * @param roleIds
//     * @param userId
//     */
//    void saveUserRoles(String roleIds,Long userId);
}
