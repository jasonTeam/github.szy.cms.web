package com.cms.szy.configuration.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.configuration.properties.RedisConfig;
import com.cms.szy.configuration.redis.cache.BaseRedisCacheImpl;
import com.cms.szy.configuration.redis.cache.CacheClient;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * redis配置
 *
 * @version
 * @author Hermit 2017年4月15日 下午3:07:39
 *
 */
@Configuration
@EnableAutoConfiguration
public class RedisCacheConfig {

	@Autowired
	private JdkSerializationRedisSerializer valueSerializer;

	@Autowired
	private StringRedisSerializer keySerializer;
	
	@Autowired
	private RedisConfig redisConfig;

	@Bean
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(redisConfig.getMaxTotal());
		config.setMaxIdle(redisConfig.getMaxIdle());
		config.setMinIdle(redisConfig.getMinIdle());
		config.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
		GwsLogger.info("JedisPoolConfig bean init success.");
		return config;
	}

	@Bean
	public JedisConnectionFactory getConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisPoolConfig config = getRedisConfig();
		factory.setPoolConfig(config);
		factory.setHostName(redisConfig.getHost());
		factory.setPort(redisConfig.getPort());
		if (StringUtils.isNotBlank(redisConfig.getPassword())) {
			factory.setPassword(redisConfig.getPassword().trim());
		}
		GwsLogger.info("JedisConnectionFactory bean init success.");
		return factory;
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setKeySerializer(keySerializer);
		template.setValueSerializer(keySerializer);  //修改前redis setList不能存List
		template.setValueSerializer(valueSerializer);  //修改后
		template.setDefaultSerializer(valueSerializer);
		template.setHashValueSerializer(valueSerializer);
		template.setConnectionFactory(factory);
		return template;
	}

	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		return stringRedisSerializer;
	}

	@Bean
	public JdkSerializationRedisSerializer jdkRedisSerializer() {
		JdkSerializationRedisSerializer jdkRedisSerializer = new JdkSerializationRedisSerializer();
		return jdkRedisSerializer;
	}

	@Bean
	public CacheClient redisClientFactory() {
		CacheClient cc = new BaseRedisCacheImpl();
		return cc;
	}

}
