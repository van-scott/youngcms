package com.esen.youngcms.core.lisenter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.esen.youngcms.core.queue.consumer.RedisConsumer;
/**
 * redisConsumer线程初始化
 * @author fumiao
 * @date 2015-12-5
 */
public class RedisConsumerInit implements ServletContextListener {
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
        RedisConsumer redisConsumer=(RedisConsumer) wac.getBean("redisConsumer");
        Thread thread=new Thread(redisConsumer);
        thread.start();
    }
    /**
     * 获取上下文对象方法
     * @return 上下方对象
     */
    public static ApplicationContext getApplicationContext(){
        return wac;
    }
}