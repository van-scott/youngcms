package com.esen.youngcms.core.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.esen.youngcms.bean.SysUser;
import com.esen.youngcms.core.annotation.OperateLog;
import com.esen.youngcms.core.utils.DateUtil;
import com.esen.youngcms.core.utils.HttpKit;
import com.esen.youngcms.core.utils.IPUtils;
import com.esen.youngcms.service.OperateLogService;

@Aspect
@Component
public class OperateLogAspect {
	
	@Autowired
	private OperateLogService operateLogService;

    @Pointcut("@annotation(com.esen.youngcms.core.annotation.OperateLog)")  
    public void operateLogCut(){
    	
    }
    
    @Around("operateLogCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//保存日志
		saveOperateLog(point, time);

		return result;
	}
    
    private void saveOperateLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		RequestMapping classRequestMapping=method.getDeclaringClass().getAnnotation(RequestMapping.class);
		OperateLog operateLog=method.getAnnotation(OperateLog.class);
		RequestMapping mothodRequestMapping= method.getAnnotation(RequestMapping.class);
		com.esen.youngcms.bean.OperateLog operateLogBean=new com.esen.youngcms.bean.OperateLog();
		if(operateLog!=null){
			operateLogBean.setModuleName(operateLog.moudleName());
			operateLogBean.setOptName(operateLog.optName());
			operateLogBean.setDescription(operateLog.description());
		}
		//请求的类名
		String className = joinPoint.getTarget().getClass().getName();
		//请求的方法名
		String methodName = signature.getName();
		//请求的参数
	    //Object[] args = joinPoint.getArgs();
		operateLogBean.setIp(IPUtils.getIpAddr(HttpKit.getRequest()));
		operateLogBean.setTime(time);
	    operateLogBean.setMethod(className+"."+methodName+"()");
	    operateLogBean.setUrl(classRequestMapping.value()[0]+mothodRequestMapping.value()[0]);
	    String username = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getRealName();
	    operateLogBean.setAuthor(username);
	    operateLogBean.setCreateTime(DateUtil.dateToStr(new Date(), 12));
	    operateLogService.insert(operateLogBean);
	}
    

}