package com.esen.youngcms.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.esen.youngcms.core.queue.command.BaseCommand;
import com.esen.youngcms.core.queue.command.ReptileContentCommand;
import com.esen.youngcms.core.queue.consumer.RedisConsumer;
import com.esen.youngcms.core.utils.redis.JedisClient;

@Configuration
public class QueueConfig {
	
	@Autowired
	private Environment env;
	 
	@Autowired
	private ReptileContentCommand reptileContentCommand;
	 
	@Bean(initMethod="init",destroyMethod="destory")
	public JedisClient jedisClient() {
		JedisClient jedisClient=JedisClient.getInstance();
		jedisClient.setAddresses(env.getProperty("redis.address"));
		jedisClient.setPassword(env.getProperty("redis.pwd"));
		jedisClient.setPort(6379);
		return jedisClient;
	}
	
	@Bean
	public RedisConsumer redisConsumer() {
		RedisConsumer redisConsumer=new RedisConsumer();
		redisConsumer.setJedisClient(jedisClient());
		redisConsumer.setTaskExecutor(new ThreadPoolTaskExecutor());
		redisConsumer.setBaseCommands(getCommands());
		return redisConsumer;
	}
	
	public Map<String, BaseCommand> getCommands() {
		Map<String, BaseCommand> commands=new HashMap<>();
		commands.put("reptileContentCommand", reptileContentCommand);
		return commands;
	}

}
