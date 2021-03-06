package com.cms.szy.configuration.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class AbstractBaseRedis {  
    @SuppressWarnings("rawtypes")
	@Autowired
    protected RedisTemplate redisTemplate;  
  
    @SuppressWarnings("rawtypes")
	public void setRedisTemplate(RedisTemplate redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
        
}  
