package com.esen.youngcms.core.lisenter;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.esen.youngcms.bean.Flow;
import com.esen.youngcms.bean.FlowFace;
import com.esen.youngcms.service.FlowFaceService;
import com.esen.youngcms.service.FlowService;
import com.esen.youngcms.vo.FlowBean;
/**
 * 加载工作流
 * @author fumiao
 * @date 2014-1-22
 */
public class FlowInit implements ServletContextListener {
	/**
	 * 上下文对象
	 */
	private static WebApplicationContext wac;
	/**
	 * 	上下文销毁时执行方法
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	/**
	 * 上下文初始时执行方法
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		wac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		FlowService flowService = (FlowService) wac.getBean("flowService");
		List<Flow> flows=flowService.selectList(null);
		FlowBean.setFlows(flows);
		FlowFaceService flowFaceService = (FlowFaceService) wac.getBean("flowFaceService");
		List<FlowFace> flowFaces=flowFaceService.selectList(null);
		FlowBean.setFlows(flows);
		FlowBean.setFlowFaces(flowFaces);
	}
	/**
	 * 获取上下文对象方法
	 * @return 上下方对象
	 */
	public static ApplicationContext getApplicationContext(){
		return wac;
	}

}
