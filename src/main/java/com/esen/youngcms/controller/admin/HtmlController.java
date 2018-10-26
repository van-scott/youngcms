package com.esen.youngcms.controller.admin;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.esen.youngcms.bean.Channel;
import com.esen.youngcms.bean.ChannelTemplate;
import com.esen.youngcms.bean.CmsModel;
import com.esen.youngcms.bean.Content;
import com.esen.youngcms.bean.ContentImage;
import com.esen.youngcms.bean.ContentOption;
import com.esen.youngcms.bean.ContentText;
import com.esen.youngcms.bean.ContentVideo;
import com.esen.youngcms.bean.Site;
import com.esen.youngcms.config.FreemarkerConfig;
import com.esen.youngcms.config.properties.CmsProperties;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.freemarker.utils.Freemarker;
import com.esen.youngcms.core.utils.DictUtil;
import com.esen.youngcms.core.utils.SessionKey;
import com.esen.youngcms.service.ChannelService;
import com.esen.youngcms.service.ChannelTemplateService;
import com.esen.youngcms.service.CmsModelService;
import com.esen.youngcms.service.ContentImageService;
import com.esen.youngcms.service.ContentOptionService;
import com.esen.youngcms.service.ContentService;
import com.esen.youngcms.service.ContentTextService;
import com.esen.youngcms.service.ContentVideoService;
import com.esen.youngcms.vo.Callback;
import com.esen.youngcms.vo.ContentDetail;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
@Controller
@RequestMapping("/admin/html/")
public class HtmlController extends AdminBaseController{

     @Autowired
     private ChannelService channelService;
     @Autowired
     private ContentService contentService;
     @Autowired
     private CmsModelService cmsModelService;
     @Autowired
     private ContentTextService contentTextService;
     @Autowired
     private ContentImageService contentImageService;
     @Autowired
     private ContentOptionService contentOptionService;
     @Autowired
     private ContentVideoService contentVideoService;
     @Autowired
     private CmsProperties cmsProperties;
     @Autowired
     private FreemarkerConfig freemarkerConfig;
     @Autowired
     private ChannelTemplateService channelTemplateService;
     
     protected Map<String, Object> data=new HashMap<String, Object>();

     @RequestMapping("treePage")
     public String treePage(Channel bean,Model model) {
    	List<Channel> list=channelService.selectList(null);
    	for (Channel channel : list) {
    		channel.setChildren(getChildren(channel.getId(),list));
    	}
        model.addAttribute(DEFAULT_PAGE_DATA, list);
        return PREFIX+"html/treePage";
     }
     
     @RequestMapping("channel")
     @ResponseBody
     public Callback create(@RequestParam(value="ids")String ids) {
    	try {
        	String[] channels=StringUtils.split(ids,",");
        	for(int i=0;i<channels.length;i++){
        		String channelId=channels[i];
        		if(StringUtils.isNotBlank(channelId)){
        			Channel channel=channelService.selectById(Integer.valueOf(channelId));
        			ChannelTemplate channelTemplate=new ChannelTemplate();
        			channelTemplate.setChannelId(Integer.valueOf(channelId));
        			channelTemplate.setModelId(0);
        			channelTemplate=channelTemplateService.selectOne(new EntityWrapper<ChannelTemplate>(channelTemplate));
        			if(StringUtils.isNotBlank(channelTemplate.getTemplate())){
        				Subject currentUser = SecurityUtils.getSubject();  
        				//模板路径
        				Site site=(Site) currentUser.getSession(true).getAttribute(SessionKey.SYS_SITE);
        		    	String tplPath=cmsProperties.getTemplatePath()+File.separator+site.getSourcePath();
        		    	String staticPath=cmsProperties.getSourcePath()+File.separator+site.getSourcePath();
        				Configuration configuration=new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            			TemplateLoader templateLoader=new FileTemplateLoader(new File(tplPath));
            			configuration.setTemplateLoader(templateLoader);
        				configuration.setSharedVaribles(freemarkerConfig.getSharedVaribles());
        				data.put("path",site.getPath());
        				data.put("domain",site.getDomain());
        				data.put("port",site.getPort());
        				data.put("channel",channel);
            	    	Freemarker.crateHTML(configuration,data,channelTemplate.getTemplate(),staticPath+File.separator+"channel"+File.separator+channel.getCode()+".html");
        			}
        		}
        	}
        	return success();
		} catch (Exception e) {
			System.out.println(e.toString());
			return error();
		}
     }
     
     @RequestMapping("content")
     @ResponseBody
     public Callback contentCreate(HttpServletRequest request) {
     	try {
     		String channelIds=request.getParameter("channelIds");
         	String[] channels=StringUtils.split(channelIds,",");
         	Map<String, Object> params=new HashMap<String, Object>();
         	params.put("channels", channels);
         	List<Content> contents=contentService.selectList(null);
         	for(int i=0;i<contents.size();i++){
         		Content content=contents.get(i);
         		ContentDetail contentDetail=new ContentDetail();
         		BeanUtils.copyProperties(contentDetail,content);
         		CmsModel model=cmsModelService.selectById(content.getModelId());
         		if(model.getHasContent()==DictUtil.getIdByNameAndEnName("hasContent", "是")){
    				ContentText contentText=new ContentText();
    				contentText.setContentId(content.getId());
    				contentText =contentTextService.selectOne(new EntityWrapper<>(contentText));
    			   if(contentText!=null){
    				   contentDetail.setContentText(contentText);
    				}
    			}
    			if(model.getHasGroupImages()==DictUtil.getIdByNameAndEnName("hasGroupImages", "是")){
    				ContentImage contentImage=new ContentImage();
    				contentImage.setContentId(content.getId());
    				List<ContentImage> list=contentImageService.selectList(new EntityWrapper<>(contentImage));
    				contentDetail.setContentImages(list);
    			}
    			if(model.getHasVedio()==DictUtil.getIdByNameAndEnName("hasVedio", "是")){
    				ContentVideo contentVideo=new ContentVideo();
    				contentVideo.setContentId(content.getId());
    				contentVideo=contentVideoService.selectOne(new EntityWrapper<>(contentVideo));
    				if(contentVideo!=null){
    					contentDetail.setContentVideo(contentVideo);
    				}
    			}
    			if(model.getHasOptions()==DictUtil.getIdByNameAndEnName("hasOptions", "是")){
    				ContentOption contentOption=new ContentOption();
    				contentOption.setContentId(content.getId());
    				List<ContentOption> list=contentOptionService.selectList(new EntityWrapper<>(contentOption));
    				contentDetail.setContentOptions(list);
    			}
    	    	Configuration configuration=new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    			TemplateLoader templateLoader=new FileTemplateLoader(new File(cmsProperties.getTemplatePath()));
    			configuration.setTemplateLoader(templateLoader);
				configuration.setSharedVaribles(freemarkerConfig.getSharedVaribles());
				data.put("path", "");
				data.put("contentDetail", contentDetail);
    	    	Freemarker.crateHTML(configuration,data,content.getTemplate(),cmsProperties.getSourcePath()+File.separator+content.getId()+".html");
         	}
         	return success();
 		} catch (Exception e) {
 			return error();
 		}
     }
     
     @RequestMapping("all")
     @ResponseBody
     public Callback all(@RequestParam(value="ids")String ids) {
    	try {
        	String[] channels=StringUtils.split(ids,",");
        	for(int i=0;i<channels.length;i++){
        		String channelId=channels[i];
        		if(StringUtils.isNotBlank(channelId)){
        			Channel channel=channelService.selectById(Integer.valueOf(channelId));
        			ChannelTemplate channelTemplate=new ChannelTemplate();
        			channelTemplate.setChannelId(Integer.valueOf(channelId));
        			channelTemplate.setModelId(0);
        			channelTemplate=channelTemplateService.selectOne(new EntityWrapper<ChannelTemplate>(channelTemplate));
        			if(StringUtils.isNotBlank(channelTemplate.getTemplate())){
        				Subject currentUser = SecurityUtils.getSubject();  
        				//模板路径
        				Site site=(Site) currentUser.getSession(true).getAttribute(SessionKey.SYS_SITE);
        		    	String tplPath=cmsProperties.getTemplatePath()+File.separator+site.getSourcePath();
        		    	String staticPath=cmsProperties.getSourcePath()+File.separator+site.getSourcePath();
        				Configuration configuration=new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            			TemplateLoader templateLoader=new FileTemplateLoader(new File(tplPath));
            			configuration.setTemplateLoader(templateLoader);
        				configuration.setSharedVaribles(freemarkerConfig.getSharedVaribles());
        				data.put("path",site.getPath());
        				data.put("domain",site.getDomain());
        				data.put("port",site.getPort());
        				data.put("channel",channel);
            	    	Freemarker.crateHTML(configuration,data,channelTemplate.getTemplate(),staticPath+File.separator+"channel"+File.separator+channel.getCode()+".html");
        			}
        		}
        	}
        	return success();
		} catch (Exception e) {
			System.out.println(e.toString());
			return error();
		}
     }
     
     @RequestMapping("index")
     @ResponseBody
     public Callback index(@RequestParam(value="ids")String ids) {
    	try {
        	String[] channels=StringUtils.split(ids,",");
        	for(int i=0;i<channels.length;i++){
        		String channelId=channels[i];
        		if(StringUtils.isNotBlank(channelId)){
        			Channel channel=channelService.selectById(Integer.valueOf(channelId));
        			ChannelTemplate channelTemplate=new ChannelTemplate();
        			channelTemplate.setChannelId(Integer.valueOf(channelId));
        			channelTemplate.setModelId(0);
        			channelTemplate=channelTemplateService.selectOne(new EntityWrapper<ChannelTemplate>(channelTemplate));
        			if(StringUtils.isNotBlank(channelTemplate.getTemplate())){
        				Subject currentUser = SecurityUtils.getSubject();  
        				//模板路径
        				Site site=(Site) currentUser.getSession(true).getAttribute(SessionKey.SYS_SITE);
        		    	String tplPath=cmsProperties.getTemplatePath()+File.separator+site.getSourcePath();
        		    	String staticPath=cmsProperties.getSourcePath()+File.separator+site.getSourcePath();
        				Configuration configuration=new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            			TemplateLoader templateLoader=new FileTemplateLoader(new File(tplPath));
            			configuration.setTemplateLoader(templateLoader);
        				configuration.setSharedVaribles(freemarkerConfig.getSharedVaribles());
        				data.put("path",site.getPath());
        				data.put("domain",site.getDomain());
        				data.put("port",site.getPort());
        				data.put("channel",channel);
            	    	Freemarker.crateHTML(configuration,data,channelTemplate.getTemplate(),staticPath+File.separator+"channel"+File.separator+channel.getCode()+".html");
        			}
        		}
        	}
        	return success();
		} catch (Exception e) {
			System.out.println(e.toString());
			return error();
		}
     }
     
     private List<Channel> getChildren(Integer id, List<Channel> channels) {
 		List<Channel> channelList=new ArrayList<Channel>();
 		for (Channel channel : channels) {
 			if(channel.getpId() !=null && channel.getpId().toString().equals(id.toString())){
 				channelList.add(channel);
 			}
 		}
 		return channelList;
 	}
}
