package com.two.lgums.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two.core.exception.BizException;
import com.two.core.exception.DaoException;
import com.two.lgums.dao.SysUserDAO;
import com.two.lgums.dao.entity.SysUserDO;
import com.two.lgums.service.SysUserService;
import com.two.lgums.service.bo.SysUserBO;

/**
 * 功能描述: 系统用户业务类
 *
 * @author: 田杰
 * @create: 2018-05-11 10:30
 **/
@Service
public class SysUserServiceImpl  implements SysUserService {
//    @Autowired
//    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserDAO sysUserDAO;
    
//    @Autowired
//    private SysRoleDAO sysRoleDao;
//    @Autowired
//    private IdWorker idWorker;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    @Override
    public SysUserBO login(String name) throws BizException {
          SysUserDO sysUserDO=null;
        try {
            sysUserDO = sysUserDAO.getSysUserByLoginName(name);
        } catch (DaoException e) {
        }
        SysUserBO sysUserBO= new SysUserBO();
        sysUserBO.setId(sysUserDO.getId());
        sysUserBO.setName(sysUserDO.getName());
        sysUserBO.setLoginName(sysUserDO.getLoginName());
        sysUserBO.setPassword(sysUserDO.getPassword());
        return sysUserBO;
    }
}
