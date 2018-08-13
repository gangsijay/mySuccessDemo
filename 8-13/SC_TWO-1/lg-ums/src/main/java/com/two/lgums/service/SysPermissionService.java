package com.two.lgums.service;

import java.util.List;

import com.two.core.base.jdbc.entity.GlobalEntity;
import com.two.core.exception.BizException;
import com.two.lgums.service.bo.MenuTreeBO;
import com.two.lgums.service.bo.SysPermissionBO;

/**
 * 功能描述:系统权限菜单接口
 *
 * @author: 田杰
 * @create: 2018/5/22 10:52
 **/
public interface SysPermissionService {
    /**
     * 功能描述: 根据平台标识查询相关菜单
     *
     * @param: [platformId:平台标识]
     * @return: java.util.List<com.cdrundle.auth.service.bo.SysPermissionBO>
     * @auther: 田杰
     * @date: 2018/5/22
     */
    List<SysPermissionBO> findAllMenu(String platformId) throws BizException;

    /**
     * 功能描述: 根据id删除菜单权限,并删除他下面的资源元素
     *
     * @param: [menuId]
     * @return: java.util.List<com.cdrundle.auth.service.bo.SysPermissionBO>
     * @auther: 田杰
     * @date: 2018/5/23
     */

    void deleteMenuById(Long menuId) throws BizException;

    /**
     * 功能描述:保存菜单
     *
     * @param: [sysPermissionDO]
     * @return: void
     * @auther: 田杰
     * @date: 2018/5/25
     */
//    void save(SysPermissionQuery sysPermission)throws BizException;
//
//    /**
//     * 功能描述:跟新菜单
//     *
//     * @param: [sysPermissionDO]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/25
//     */
//    void update(SysPermissionQuery sysPermission)throws BizException;
    
    /**
     * 获取菜单树
     * @param plat
     * @param pId
     * @return
     * @throws BizException
     */
    List<MenuTreeBO> getMenuList(String plat,Long pId) throws BizException;
    /**
     * 保存角色权限
     * @param menuId
     * @param elementId
     * @param roleId
     * @throws BizException
     */
    void savePermission(String[] menuId,String[] elementId,long roleId) throws BizException;
    /**
     * 查询角色权限
     * @param roleId
     * @return
     * @throws BizException
     */
    List<GlobalEntity> getRoleCheckPermission(Long roleId)throws BizException;
}
