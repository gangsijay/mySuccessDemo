package com.zhm.service;

import com.zhm.dao.OauthClientDetailsDao;
import com.zhm.db.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhm on 17-3-13.
 */
@Service("oauthClientDetailsService")
public class OauthClientDetailsService {
    @Autowired
    private OauthClientDetailsDao oauthClientDetailsDao;

    /**
     * 根据clientId查找App应用
     * @param clientId
     * @return
     */
    public OauthClientDetails findByClientid(String clientId) {
        return oauthClientDetailsDao.findByClientid(clientId);
    }
}
