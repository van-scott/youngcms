package com.esen.youngcms.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

@Configuration   
@MapperScan(basePackages="com.esen.youngcms.dao")
public class MyBatisConfig {
    
    @Autowired
    private Environment env;
    
    /**
	 * 创建数据源
	 * 
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	 */
	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
		props.put("url", env.getProperty("jdbc.url"));
		props.put("username", env.getProperty("jdbc.username"));
		props.put("password", env.getProperty("jdbc.password"));
		props.put("connectionInitSqls", "set names utf8mb4;");
		props.setProperty("filters", "stat,wall");
		return DruidDataSourceFactory.createDataSource(props);
	}
	
	/**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(DBType.MYSQL.getDb());
        return paginationInterceptor;
    }

}