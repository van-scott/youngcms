package com.esen.youngcms.core.lisenter;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.esen.youngcms.bean.Dict;
import com.esen.youngcms.service.DictService;
import com.esen.youngcms.vo.DictBean;
/**
 * 加载字典表
 * @author fumiao
 * @date 2014-1-22
 */
public class DictInit implements ServletContextListener {
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
		DictService dictService = (DictService) wac.getBean("dictService");
		List<Dict> list=dictService.selectAll(new EntityWrapper<Dict>());
		DictBean.setDicts(list);
	}
	/**
	 * 获取上下文对象方法
	 * @return 上下方对象
	 */
	public static ApplicationContext getApplicationContext(){
		return wac;
	}

}
