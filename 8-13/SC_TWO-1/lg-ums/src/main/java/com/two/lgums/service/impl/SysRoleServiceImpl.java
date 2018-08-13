package com.two.lgums.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two.core.exception.BizException;
import com.two.core.exception.DaoException;
import com.two.lgums.dao.SysRoleDAO;
import com.two.lgums.dao.entity.SysRoleDO;
import com.two.lgums.service.SysRoleService;
import com.two.lgums.service.bo.SysRoleBO;

/**
 * 功能描述: ${description}
 *
 * @author: 田杰
 * @create: 2018-05-15 16:18
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    SysRoleDAO sysRoleDAO;

//    @Autowired
//    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRoleBO> getRoleByUserId(Long id) throws BizException {
        List<SysRoleDO> roleByUserLi;
        try {
            roleByUserLi = sysRoleDAO.getRoleByUserId(id);
        } catch (DaoException e) {
            throw BizException.wrap(e.getCode(), e.getMessage());
        }
        List<SysRoleBO> list = new ArrayList<>();
        for (SysRoleDO role : roleByUserLi) {
        	SysRoleBO bo = new SysRoleBO();
        	bo.setCode(role.getCode());
        	bo.setName(role.getName());
        	list.add(bo);
        }
//        return sysRoleMapper.toBOList(roleByUserLi);
        return list;
    }

//    /**
//     * 功能描述: 根据查询条件查询拥有角色
//     *
//     * @param: [roleQuery]
//     * @return: java.util.List<com.cdrundle.auth.dao.entity.SysRoleDO>
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    @Override
//    public List<SysRoleBO> findListRole(SysRoleQuery roleQuery) throws BizException {
//        List<SysRoleDO> listRoleDo = Collections.EMPTY_LIST;
//
//        try {
//            listRoleDo = sysRoleDAO.findListRole(roleQuery);
//        } catch (DaoException e) {
//            log.error(BizErrorCode.BIZ_PERMISSION_QUERY_ROLE_ERROR.describe, e);
//            throw BizException.wrap(BizErrorCode.BIZ_PERMISSION_QUERY_ROLE_ERROR);
//        }
//        return sysRoleMapper.toBOList(listRoleDo);
//    }
//
//    /**
//     * 功能描述:
//     *
//     * @param: [pageQuery]
//     * @return: com.cdrundle.common.util.Page<com.cdrundle.auth.dao.entity.SysRoleDO>
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    @Override
//    public Page<SysRoleBO> findByPage(SysRoleQuery pageQuery) throws BizException {
//        Page<SysRoleDO> sysRoleDOPage = null;
//        try {
//            sysRoleDOPage = sysRoleDAO.findByPage(pageQuery);
//        } catch (DaoException e) {
//            log.error(BizErrorCode.BIZ_PERMISSION_QUERY_ROLE_ERROR.describe, e);
//            throw BizException.wrap(BizErrorCode.BIZ_PERMISSION_QUERY_ROLE_ERROR);
//        }
//        return sysRoleMapper.toBOPage(sysRoleDOPage);
//    }
//
//    /**
//     * 功能描述: 保存角色信息
//     *
//     * @param: [sysRoleBO]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    @Override
//    public void save(SysRoleBO sysRoleBO) throws BizException {
//        SysRoleDO sysRoleDO = sysRoleMapper.toDo(sysRoleBO);
//        try {
//            sysRoleDAO.save(sysRoleDO);
//        } catch (DaoException e) {
//            log.error(BizErrorCode.BIZ_PERMISSION_SAVE_ROLE_ERROR.describe, e);
//            throw BizException.wrap(BizErrorCode.BIZ_PERMISSION_SAVE_ROLE_ERROR);
//        }
//    }
//
//    /**
//     * 功能描述: 更新角色信息
//     *
//     * @param: [sysRoleBO]
//     * @return: void
//     * @auther: 田杰
//     * @date: 2018/5/28
//     */
//    @Override
//    public void update(SysRoleBO sysRoleBO) throws BizException {
//        SysRoleDO sysRoleDO = sysRoleMapper.toDo(sysRoleBO);
//        try {
//            sysRoleDAO.update(sysRoleDO);
//        } catch (DaoException e) {
//            log.error(BizErrorCode.BIZ_PERMISSION_UPDATE_ROLE_ERROR.describe, e);
//            throw BizException.wrap(BizErrorCode.BIZ_PERMISSION_UPDATE_ROLE_ERROR);
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
//    @Transactional
//    public void deleteById(Long id) throws BizException {
//        try {
//            sysRoleDAO.deleteById(id);
//        } catch (DaoException e) {
//            log.error(BizErrorCode.BIZ_PERMISSION_UPDATE_ROLE_ERROR.describe, e);
//            throw BizException.wrap(BizErrorCode.BIZ_PERMISSION_UPDATE_ROLE_ERROR);
//        }
//    }
//    
//    
//    
//    
//    
//    
//    @Override
//	public List<SysRoleBO> findRoleByOrgId(Long id) throws BizException {
//		List<SysRoleDO> roles=null;
//		try {
//			roles=sysRoleDAO.findRoleByOrgId(id);
//		} catch (DaoException e) {
//			throw BizException.wrap(e.getCode(), e.getMessage());
//		}  
//		return sysRoleMapper.toBOList(roles);
//	}
//
//	@Override
//	public int saveRole(SysRoleDO role) throws BizException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int deleteRoleById(long id) throws BizException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateRoleById(long id) throws BizException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<Long> getUserRoles(long id) throws BizException {
//		
//		try {
//			List<SysUserRoleDO> list=sysRoleDAO.getUserRoles(id);
//			List<Long> roleIds=new ArrayList<>();
//			for(SysUserRoleDO l:list) {
//				roleIds.add(l.getSysRoleId());
//			}
//			return  roleIds;
//		} catch (DaoException e) {
//			throw BizException.wrap(e.getCode(), e.getMessage());
//		}
//	}
//
//	@Override
//	public List<SysRoleBO> getUserHaveRole(Long id) throws BizException {
//		List<SysRoleDO> roleList=null;
//		try {
//			 roleList=sysRoleDAO.getUserHaveRole(id);
//		} catch (DaoException e) {
//			
//			throw BizException.wrap(e.getCode(), e.getMessage());
//		}
//		return sysRoleMapper.toBOList(roleList);
//	}
//
//	@Override
//	public Page<SysRoleBO> getUserNotHaveRole(Long id,SysRoleQuery sysRoleQuery) throws BizException {
//		Page<SysRoleDO> rolePage=null;
//		try {
//			rolePage=sysRoleDAO.getUserNotHaveRole(id,sysRoleQuery);
//		} catch (DaoException e) {
//			throw BizException.wrap(e.getCode(), e.getMessage());
//		}
//		return sysRoleMapper.toBOPage(rolePage);
//	}
    
    
    
    
    
    
    
}
