package com.gzzdsg.happylife.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 配置类
 *
 * @author: damei
 */
@Configuration
public class RedisConfig {

    /**
     * 注入一个redis的操作模板
     *
     * @param connectionFactory redis的连接工厂
     * @return redis操作模板
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // 设置序列化器
        template.setDefaultSerializer(new StringRedisSerializer());
        return template;
    }
}
