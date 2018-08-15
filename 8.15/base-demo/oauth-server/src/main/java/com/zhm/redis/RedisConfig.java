package com.zhm.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;

/**
 * Created by zhm on 17-3-13.
 * redis配置
 */
@Configuration
public class RedisConfig {
    @Autowired
    private JedisConnectionFactory connectionFactory;
    @Bean
    public RedisTemplate<String, Map> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Map> template = new RedisTemplate<String, Map>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
