package com.esen.youngcms.vo;


/**
 * 操作结果类[可用于ajax返回结果记录对象]
 * @author liuxuewen
 * @date 2014-1-22
 */
public class Callback{
	
	private String statusCode="200";
	private String message="操作成功";
	private String navTabId;
	private String rel;
	private String callbackType="closeCurrent";
	private String forwardUrl;
	private boolean success;
	private Object result;
	
	public Callback() {
		super();
	}
	
	public Callback(String statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public Callback( boolean success,String message) {
		super();
		this.message = message;
		this.success = success;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNavTabId() {
		return navTabId;
	}
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getCallbackType() {
		return callbackType;
	}
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
