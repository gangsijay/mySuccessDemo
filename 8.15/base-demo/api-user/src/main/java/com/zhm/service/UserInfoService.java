package com.zhm.service;

import com.zhm.dao.AppUserDao;
import com.zhm.dao.UserInfoDao;
import com.zhm.db.AppUser;
import com.zhm.db.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhm on 17-3-14.
 */
@Service("userInfoService")
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private AppUserDao appUserDao;

    public UserInfo findByMobile(String mobile) {
        return userInfoDao.findByMobile(mobile);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserInfo uinfo, AppUser appUser) throws Exception{
        try {
            userInfoDao.addUser(uinfo);
            appUserDao.addInfo(appUser);
        } catch (Exception e) {
           throw e;
        }
    }

    public void updateUser(UserInfo uinfo) {
        userInfoDao.updateUser(uinfo);
    }

    public UserInfo findById(Integer id) {
        return userInfoDao.findById(id);
    }

    public UserInfo findByUserid(String userid) {
        return userInfoDao.findByUserid(userid);
    }

    public void updateUserPassword(String userid, String password) {
        userInfoDao.updateUserPassword(userid,password);
    }

    public int checkMobileExists(String mobile) {
        return userInfoDao.checkMobileExists(mobile);
    }

    public int checkEmailExists(String email) {
        return userInfoDao.checkEmailExists(email);
    }

    public int checkUsernameExists(String username) {
        return userInfoDao.checkUsernameExists(username);
    }
}
