package com.esen.youngcms.core.utils;

import java.util.Map;

public class MessageDate {
	
	public final static String REDIS_KEY="LIST_MESSAGE";
	
	private String commandName;
	private Map<String, Object> params;
	
	public String getCommandName() {
		return commandName;
	}
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
