package com.px.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

/**
 * @author:terry
 * @create: 2024-04-18 11:49
 * @Description: redis自增
 */

@Component
public class IdGeneratorSerivce {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public long generateId(String key) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }
}
