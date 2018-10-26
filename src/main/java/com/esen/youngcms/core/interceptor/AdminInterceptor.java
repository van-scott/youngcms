package com.esen.youngcms.core.interceptor;

import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.esen.youngcms.bean.Module;
import com.esen.youngcms.bean.SysUser;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.annotation.PassCheck;
import com.esen.youngcms.core.utils.SessionKey;
import com.esen.youngcms.vo.Callback;

public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		String url=request.getRequestURI();
		if(object instanceof HandlerMethod){
			HandlerMethod method=(HandlerMethod) object;
			PassCheck passCheck=method.getMethodAnnotation(PassCheck.class);
			if(method.getBean() instanceof AdminBaseController){
					if(request.getSession(true).getAttribute(SessionKey.SYS_USER)==null){
					   response.sendRedirect("/admin/loginForm");
					   return false;
					}else{
						if(passCheck==null){
						Boolean b=true;
						Map<String, Module> map=(Map<String, Module>) request.getSession(true).getAttribute(SessionKey.SYS_MODULE);
						for (Entry<String, Module> entry : map.entrySet()) {
							Module module=entry.getValue();
							if(url.contains(module.getHref())){
								b=true;
								break;
							}
						}
						SysUser sysUser=(SysUser) request.getSession(true).getAttribute(SessionKey.SYS_USER);
						String loginName=sysUser.getLoginName();
						if(!"admin".equals(loginName) && (url.contains("delete") || url.contains("addOrUpdate") )){
							b=false;
						}
						String requestType = request.getHeader("X-Requested-With");  
						if(!b){
							if("XMLHttpRequest".equals(requestType)){
								response.setContentType("text/xml; charset=utf-8");
								Callback callback=new Callback();
								callback.setSuccess(false);
								callback.setMessage("非常遗憾，您没有权限操作");
								Writer out=response.getWriter();
								out.write(JSON.toJSONString(callback));
							}else{
								response.sendRedirect("/view/admin/error.jsp");
							}
							    return false;
						}
					}
				}
			}
		}
		return true;
	}

}
