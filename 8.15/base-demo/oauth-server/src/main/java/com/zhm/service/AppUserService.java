package com.zhm.service;

import com.zhm.dao.AppUserDao;
import com.zhm.db.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-3-13.
 */
@Service("appUserService")
public class AppUserService {
    @Autowired
    private AppUserDao appUserDao;

    /**
     * 找出用户允许访问的所有应用
     * @param userid
     * @return
     */
    public List<AppUser> findListByUserid(String userid) {
        return appUserDao.findListByUserid(userid);
    }
    
    /**
     * 判断该用户是否允许访问App应用
     * @param clientId
     * @param userid
     * @return
     */
    public long checkUserAllowVisitApp(String clientId, String userid) {
        return appUserDao.checkUserAllowVisitApp(clientId,userid);
    }
}
