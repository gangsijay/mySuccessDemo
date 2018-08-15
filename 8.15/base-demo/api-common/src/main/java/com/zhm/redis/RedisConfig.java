package com.zhm.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
/**
 * Created by zhm on 17-3-13.
 * redis配置
 */
@Configuration
public class RedisConfig {
    @Autowired
    private JedisConnectionFactory connectionFactory;
}
