package com.two.dao;
import com.two.pojo.SysUser;
 
/**
 * 系统用户dao
 *
 * @Author: 我爱大金子
 * @Description: 系统用户dao
 * @Date: Create in 16:15 2017/7/5
 */
public interface SysUserDao {
    public SysUser findByUserName(String username);
}