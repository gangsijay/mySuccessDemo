package com.two.secuoauth.dao;
import java.util.List;

import com.two.secuoauth.pojo.SysPermission;
 
/**
 * 系统权限dao
 *
 * @Author: 我爱大金子
 * @Description: 描述
 * @Date: Create in 17:05 2017/7/5
 */
public interface SysPermissionDao {
    List<SysPermission> findAll();
    List<SysPermission> findByAdminUserId(Long userId) ;
}