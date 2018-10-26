package com.esen.youngcms.config;

import javax.servlet.ServletContextListener;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.esen.youngcms.core.lisenter.DictInit;
import com.esen.youngcms.core.lisenter.FlowInit;
import com.esen.youngcms.core.lisenter.RedisConsumerInit;

@Configuration
@AutoConfigureAfter(value={QueueConfig.class})
public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public ServletListenerRegistrationBean<ServletContextListener> DictListener() {
		ServletListenerRegistrationBean<ServletContextListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<ServletContextListener>();
		servletListenerRegistrationBean.setListener(new DictInit());
		return servletListenerRegistrationBean;
	}
	
	@Bean
	public ServletListenerRegistrationBean<ServletContextListener> FlowListener() {
		ServletListenerRegistrationBean<ServletContextListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<ServletContextListener>();
		servletListenerRegistrationBean.setListener(new FlowInit());
		return servletListenerRegistrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean<ServletContextListener> redisConsumerListener() {
		ServletListenerRegistrationBean<ServletContextListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<ServletContextListener>();
		servletListenerRegistrationBean.setListener(new RedisConsumerInit());
		return servletListenerRegistrationBean;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webapp/static/**").addResourceLocations("classpath:/webapp/static/");
		registry.addResourceHandler("/webapp/pages/**").addResourceLocations("classpath:/webapp/pages/");
		super.addResourceHandlers(registry);
	}
	
}
