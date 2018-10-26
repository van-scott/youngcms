package com.esen.youngcms.core.aspect;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esen.youngcms.bean.CmsContent;
import com.esen.youngcms.core.freemarker.AdvertList;
import com.esen.youngcms.core.freemarker.ChannelList;
import com.esen.youngcms.core.freemarker.FriendLinkList;
import com.esen.youngcms.core.freemarker.utils.Freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

@Aspect
@Component
public class CreateHtmlAspect {

    protected Configuration freemarkerCfg;
	
	protected Map<String, Object> data=new HashMap<String, Object>();
	@Autowired
	protected ServletContext context;
	@Autowired
	protected ChannelList channelList;
	@Autowired
	protected AdvertList advertList;
	@Autowired
	protected FriendLinkList friendLinkList ;
    
    //切点  
    @Pointcut("@annotation(com.esen.youngcms.core.annotation.CreateHtml)")  
    public void createHtmlAspect(){  
    
    } 
    
    @After("createHtmlAspect()")
    public void createHtml(JoinPoint jp) throws Throwable {
        System.out.println("拦截进行中");
        String methodName=jp.getSignature().getName();
    	Method[] methods=jp.getTarget().getClass().getMethods();
    	for(Method method:methods){
    		if(method.getName().equals(methodName)){
    		 Object[] objects=jp.getArgs();
    		 for (Object object : objects) {
     			if(object instanceof CmsContent){
     				try {
							this.initCfg();
						} catch (TemplateModelException e) {
							e.printStackTrace();
						}
     				CmsContent cmsContent=(CmsContent) object;
     				data.put("cmsContent", cmsContent);
     				String dir=cmsContent.getCreateTime().substring(0, 10);
     				String htmlPath = context.getRealPath("/html/"+dir+"");
     				File htmlFile = new File(htmlPath);
     				if(!htmlFile.exists()){
     					htmlFile.mkdir();
     				}
     				Freemarker.crateHTML(freemarkerCfg, data, "/default/cmsContent/detail.html","/"+dir+"/"+cmsContent.getId()+".html");
     			}
     		}
    		}
    	}
    }
    
  	public void  initCfg() throws TemplateModelException{
		data.put("path", context.getContextPath());
  		//判断context中是否有freemarkerCfg属性
  		if (context.getAttribute("freemarkerCfg")!=null) {
  			freemarkerCfg=(Configuration)context.getAttribute("freemarkerCfg");
  		}else {
  			freemarkerCfg = new Configuration();        
  			//加载模版        
  			freemarkerCfg.setServletContextForTemplateLoading(context, "/template");        
  			freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");    
  			//加载自定义标签
  			//栏目宏
  			freemarkerCfg.setSharedVariable("channelList", channelList);
  			//广告宏
  			freemarkerCfg.setSharedVariable("advertList", advertList);
  			//友链宏
  			freemarkerCfg.setSharedVariable("friendLinkList", friendLinkList);
  		}
  	}
  	

}