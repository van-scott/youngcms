package com.esen.youngcms.core.freemarker;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Repository;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
/**
 * URL转换
 * @author fumiao-pc
 *
 */
@Repository
public class EncodeURL implements TemplateMethodModel {
	
	
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		String url="";
		try {
			 if(arguments.size()!=1)   //限定方法中必须且只能传递一个参数  
		        {  
		            throw new TemplateModelException("Wrong arguments!");  
		        }  
		        url= URLEncoder.encode((String)arguments.get(0), "utf-8");
		} catch (Exception e) {
		}
		return url;
	}

}
