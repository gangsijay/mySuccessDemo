package com.zhm.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

import static org.springframework.data.redis.connection.DataType.*;

/**
 * Created by zhm on 17-2-28.
 */
@Service("oAuthSessionService")
public class OAuthSessionService {
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String deleteSession(String email) {
        try {
            String key = "spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:"+email;
            DataType type = stringRedisTemplate.type(key);
            if(NONE == type) {
                logger.info("key不存在");
                return null;
            }
            if(SET == type) {

                Set<String> results = stringRedisTemplate.opsForSet().members(key);
                Iterator<String> subkeys = results.iterator();
                while (subkeys.hasNext()){
                    String tmp = subkeys.next();
                    String[] tmpAry = tmp.split("\\$");
                    if(tmpAry.length>=1){
                        String subkey=tmpAry[1];
                        stringRedisTemplate.delete("spring:session:sessions:"+subkey);
                        stringRedisTemplate.delete("spring:session:sessions:expires:"+subkey);
                    }
                }
                stringRedisTemplate.delete(key);
            }
        } catch (Exception e) {
            logger.info("查询错误:"+e.getMessage());
            return "ERROR";
        }
        return "SUCCESS";
    }

}
