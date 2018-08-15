package com.zhm.service;

import com.zhm.dao.UserInfoDao;
import com.zhm.db.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhm on 17-3-13.
 */
@Service("userInfoService")
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 根据手机号或邮箱查询用户
     * @param username
     * @return
     */
    public UserInfo findByMobileOrEmail(String username) {
        return userInfoDao.findByMobileOrEmail(username);
    }

    /**
     * 根据Email查询用户信息
     * @param email
     * @return
     */
    public UserInfo findByEmail(String email) {
        return userInfoDao.findByEmail(email);
    }
}
