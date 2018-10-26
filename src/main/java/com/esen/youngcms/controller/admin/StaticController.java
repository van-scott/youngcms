package com.esen.youngcms.controller.admin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esen.youngcms.bean.Site;
import com.esen.youngcms.config.properties.CmsProperties;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.DateUtil;
import com.esen.youngcms.core.utils.FileUtil;
import com.esen.youngcms.core.utils.SessionKey;
import com.esen.youngcms.vo.Source;
@Controller
@RequestMapping("/admin/static/")
public class StaticController extends AdminBaseController{
	
	 @Autowired
	 private CmsProperties cmsProperties;
	
	 @RequestMapping("dataPage")
     public String dataPage(Source source,Model model) {
       return PREFIX+"static/dataPage";
     }
	 
	 @RequestMapping("fileTree")
     @ResponseBody
     public List<Source> fileTree(Source source) {
    	 Subject currentUser = SecurityUtils.getSubject();  
    	 Site site=(Site) currentUser.getSession(true).getAttribute(SessionKey.SYS_SITE);
    	 String rootPath=cmsProperties.getSourcePath()+File.separator+site.getSourcePath();
    	 FileUtil fileUtil=new FileUtil();
    	 Source root=new Source();
    	 root.setName("根资源");
    	 root.setAbsolutePath(rootPath);
    	 root.setRelativePath("");
    	 root.setId(1);
    	 root.setOpen(true);
    	 String[] excludeSuffix={"html"};
    	 String[] excludeName={"channel"};
		 return fileUtil.readfile(root,excludeSuffix,excludeName);
     }
	 
	 @RequestMapping("node")
     public String node(@RequestParam(value="tabId",required=false)String tabId,Source source) {
		try {
			source.setAbsolutePath(URLDecoder.decode(source.getAbsolutePath(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 File file=new File(source.getAbsolutePath());
		 if (file.isDirectory()) {
			 return "forward:/admin/static/list";
		 }else{
			 return "forward:/admin/static/form";
		 }
     }
	 
	 
	 @RequestMapping("list")
     public String list(@RequestParam(value="tabId",required=false)String tabId,Source source,Model model) {
		 List<Source> list=new ArrayList<>();
		 try {
			  source.setAbsolutePath(URLDecoder.decode(source.getAbsolutePath(), "UTF-8"));
			  File file=new File(source.getAbsolutePath());
			  if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(source.getAbsolutePath() + File.separator + filelist[i]);
					String fileName=readfile.getName();
					Source fileSource = new Source();
					fileSource.setId(list.size() + 1);
					fileSource.setName(fileName);
					fileSource.setpId(source.getId());
					fileSource.setAbsolutePath(readfile.getPath());
					if(readfile.isDirectory()){
						fileSource.setDocumentType("dir");
					}else{
						fileSource.setDocumentType(fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()));
					}
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(readfile.lastModified());
					fileSource.setLastModified(DateUtil.dateToStr(cal.getTime(), 12));
					fileSource.setLen(readfile.length());
					list.add(fileSource);
				}
			}
		} catch (Exception e) {
		}
		 model.addAttribute(DEFAULT_PAGE_DATA, list);
		 model.addAttribute("source", source);
		 return PREFIX+"static/list";
     }

	@RequestMapping("form")
     public String form(@RequestParam(value="tabId",required=false)String tabId,Source source,Model model) {
		try {
			  source.setAbsolutePath(URLDecoder.decode(source.getAbsolutePath(), "UTF-8"));
			  File file=new File(source.getAbsolutePath());
			  source.setName(file.getName());
	    	  String fileCode="";
	    	  if(!file.isDirectory()) {
	    		  BufferedReader reader = null;
	    	      reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
	    	      String tempString = "";
	    	      while ((tempString = reader.readLine()) != null) {
	    	    	       fileCode+=tempString+"\r\n";
	    	            }
	    	       reader.close();
	    	  }
	    	  source.setContent(StringEscapeUtils.escapeHtml3(fileCode));
	    	  model.addAttribute(DEFAULT_PAGE_FORM, source);
	    	  model.addAttribute("tabId", tabId);
		} catch (Exception e) {
		}
		 return PREFIX+"static/form";
    	  
     }
}
