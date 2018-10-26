package com.esen.youngcms.core.queue.consumer;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esen.youngcms.core.queue.command.BaseCommand;
import com.esen.youngcms.core.utils.redis.JedisClient;


/**
 * @author fumiao
 * @version 1.0
 */
public class RedisConsumer implements Runnable
{
	private final static Logger LOGGER= LoggerFactory.getLogger(RedisConsumer.class);
	private final static String REDIS_KEY="LIST_MESSAGE";
	
	private ThreadPoolTaskExecutor taskExecutor;
    private JedisClient jedisClient;
    private Map<String, BaseCommand> baseCommands;
    
	public RedisConsumer() {
		super();
	}

	@Override
	public void run() {
		while (true) {
			JedisClient jedisClient= JedisClient.getInstance();    
			JedisClient.Lists lists=jedisClient.new Lists();
			List<String> list=lists.brpop(30,REDIS_KEY);
			if(list!=null && list.size()>0){
			  String message=list.get(1);
			  try {
				    LOGGER.info(message);
				    taskExecutor.execute(new Message(message));
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
	class Message implements Runnable{
		String message="";
		public Message(String message) {
			this.message=message;
		}
		@Override
		public void run() {
			try {
				JSONObject jsonObject =JSON.parseObject(message);
	        	String commandName=jsonObject.getString("commandName");
	        	JSONObject params=jsonObject.getJSONObject("params");
	        	BaseCommand baseCommand=baseCommands.get(commandName);
	        	if(baseCommand!=null){
	        		baseCommand.execute(params.toString());
	        	}else{
	        		LOGGER.error("not fond command");
	        	}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public Map<String, BaseCommand> getBaseCommands() {
		return baseCommands;
	}

	public void setBaseCommands(Map<String, BaseCommand> baseCommands) {
		this.baseCommands = baseCommands;
	}

	public JedisClient getJedisClient() {
		return jedisClient;
	}

	public void setJedisClient(JedisClient jedisClient) {
		this.jedisClient = jedisClient;
	}

}
