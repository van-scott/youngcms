package com.esen.youngcms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * SpringBoot方式启动类
 *
 * @author fumiao
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableWebMvc
public class MainApplicion extends WebMvcConfigurerAdapter{

    protected final static Logger logger = LoggerFactory.getLogger(MainApplicion.class);
    
    public static void main(String[] args) {
        SpringApplication.run(MainApplicion.class, args);
        logger.info("MainApplicion is sussess!");
    }
}
