package com.esen.youngcms.controller.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.core.utils.HttpKit;
import com.esen.youngcms.vo.Callback;

public abstract class AdminBaseController{
	
	public static final String PREFIX = "admin/";

	public static final String RESULE_SUCCESS = "操作成功！";

	public static final String RESULE_ERROR_CODE = "300";

	public static final String RESULE_ERROR = "操作失败！";

	public static final String RESULE_SUCCESS_DELETE = "删除成功！";

	public static final String RESULE_ERROR_DELETE = "删除失败！";

	public static final String DEFAULT_PAGE_DATA = "pageData";

	public static final String DEFAULT_PAGE_FORM = "pageForm";

	protected <T> Map<String, Object> getPageData(Page<T> page, T t) {
		Map<String, Object> pageData = new HashMap<>();
		pageData.put("page", page);
		pageData.put("bean", t);
		return pageData;
	}

	protected Callback success() {
		return new Callback();
	}

	protected Callback error() {
		return new Callback(RESULE_ERROR_CODE, RESULE_ERROR);
	}

	protected Callback callback(String statusCode, String message) {
		return new Callback(statusCode, message);
	}

	protected <T> Page<T> getPage() {
		int _numPerPage = 20, _pageNum = 1;
		if (getPara("pageNum") != null) {
			_pageNum = Integer.parseInt(getPara("pageNum"));
		}
		if (getPara("numPerPage") != null) {
			_numPerPage = Integer.parseInt(getPara("numPerPage"));
		}
		return new Page<T>(_pageNum, _numPerPage);
	}

	protected String toJson(Object object) {
		return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
	}

	protected String toJson(Object object, String format) {
		if (format == null) {
			return toJson(object);
		}
		return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
	}

	protected HttpServletRequest getRequest() {
		return HttpKit.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return HttpKit.getResponse();
	}

	protected HttpSession getSession() {
		return HttpKit.getRequest().getSession();
	}

	protected Object getSessionAttr(String sessionKey) {
		return HttpKit.getRequest().getSession().getAttribute(sessionKey);
	}

	protected HttpSession getSession(Boolean flag) {
		return HttpKit.getRequest().getSession(flag);
	}

	protected String getPara(String name) {
		return HttpKit.getRequest().getParameter(name);
	}

	protected void setAttr(String name, Object value) {
		HttpKit.getRequest().setAttribute(name, value);
	}

}
