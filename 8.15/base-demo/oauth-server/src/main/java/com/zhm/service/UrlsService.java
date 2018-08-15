package com.zhm.service;

import com.zhm.dao.UrlsDao;
import com.zhm.db.Urls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-3-13.
 */
@Service("urlsService")
public class UrlsService {
    @Autowired
    private UrlsDao urlsDao;

    /**
     * 找出应用的所有权限
     * @param clientId
     * @return
     */
    public List<Urls> findAllByClientid(String clientId) {
        return urlsDao.findAllByClientid(clientId);
    }

    /**
     * 根据用户角色与应用ID找出权限
     * @param roleid
     * @param clientId
     * @return
     */
    public List<Urls> findUrlByUserRoleAndClientid(String roleid, String clientId) {
        return urlsDao.findUrlByUserRoleAndClientid(roleid,clientId);
    }

    /**
     * 根据用户与应用ID找出权限
     * @param userid
     * @param clientId
     * @return
     */
    public List<Urls> findUrlsByUserAndClientid(String userid, String clientId) {
        return urlsDao.findUrlsByUserAndClientid(userid,clientId);
    }
}
