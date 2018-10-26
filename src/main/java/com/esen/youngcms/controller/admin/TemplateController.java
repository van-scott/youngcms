package com.esen.youngcms.controller.admin;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.esen.youngcms.vo.Callback;
import com.esen.youngcms.vo.Source;
@Controller
@RequestMapping("/admin/template/")
public class TemplateController extends AdminBaseController{
	
	@Autowired
	private CmsProperties cmsProperties;
	
	@RequestMapping("dataPage")
    public String dataPage(Source source,Model model) {
       return PREFIX+"template/dataPage";
    }
	
	 @RequestMapping("fileTree")
     @ResponseBody
     public List<Source> list(Source source) {
    	 Subject currentUser = SecurityUtils.getSubject();  
    	 Site site=(Site) currentUser.getSession(true).getAttribute(SessionKey.SYS_SITE);
    	 String rootPath=cmsProperties.getTemplatePath()+File.separator+site.getSourcePath();
    	 FileUtil fileUtil=new FileUtil();
    	 Source root=new Source();
    	 root.setName("根模板");
    	 root.setAbsolutePath(rootPath);
    	 root.setRelativePath("");
    	 root.setId(1);
    	 root.setOpen(true);
    	 String[] excludeSuffix={};
    	 String[] excludeName={};
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
			 return "forward:/admin/template/list";
		 }else{
			 return "forward:/admin/template/form";
		 }
     }
	 
	 
	 @RequestMapping("list")
     public String list(Source source,Model model) {
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
						fileSource.setDocumentType(fileName.substring(fileName.indexOf(".")+1,fileName.length()));
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
		 return PREFIX+"template/list";
     }

     /**
      * 修改页面
      * @param source
      * @param model
      * @return
      */
	 @RequestMapping("form")
     public String form(Source source,Model model) {
		try {
			 if(source.getAbsolutePath()!=null){//修改文件
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
			 }
	    	  model.addAttribute(DEFAULT_PAGE_FORM, source);
		} catch (Exception e) {
		}
		 return PREFIX+"template/form";
     }
	 /**
	  * 修改提交
	  * @param source
	  * @param model
	  * @return
	  */
	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback addOrUpdate(Source source, Model model) {
		try {
			File file = null;
			if(StringUtils.isNotBlank(source.getAbsolutePath())){
				file=new File(source.getAbsolutePath());
			}else{
				file=new File(source.getParentPath()+File.separator+source.getName());
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = null;
			try {
				FileWriter fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write(source.getContent());
				bw.flush();
			} catch (Exception e) {
				System.out.println(e.toString());
			} finally {
				if (bw != null) {
					bw.close();
				}
			}
			return success();
		} catch (Exception e) {
			return error();
		}
	}
	/**
	 * 下载
	 * @param source
	 * @param model
	 * @return
	 */
	@RequestMapping("downlod")
    public String downlod(Source source,Model model) {
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
		} catch (Exception e) {
		}
		 return PREFIX+"template/form";
   	  
    }
	/**
	 * 重命名
	 * @param source
	 * @param model
	 * @return
	 */
	@RequestMapping("rename")
    public String rename(Source source,Model model) {
		try {
			  source.setAbsolutePath(URLDecoder.decode(source.getAbsolutePath(), "UTF-8"));
			  File file=new File(source.getAbsolutePath());
			  source.setName(file.getName());
	    	  model.addAttribute(DEFAULT_PAGE_FORM, source);
		} catch (Exception e) {
		}
		 return PREFIX+"template/rename";
   	  
    }
	
	/**
	 * 重命名
	 * @param source
	 * @param model
	 * @return
	 */
	@RequestMapping("renameCommit")
	@ResponseBody
    public Callback renameCommit(Source source,Model model) {
		try {
			File file=new File(URLDecoder.decode(source.getAbsolutePath(), "UTF-8"));
			file.renameTo(new File(source.getParentPath()+File.separator+source.getName()));
	    	  return success();
		} catch (Exception e) {
			  return error();
		}
   	  
    }
	
	/**
	 * 新建文件夹
	 * @param source
	 * @param model
	 * @return
	 */
	@RequestMapping("mkdir")
    public String mkdir(Source source,Model model) {
		try {
	    	  model.addAttribute(DEFAULT_PAGE_FORM, source);
		} catch (Exception e) {
			
		}
		 return PREFIX+"template/mkdir";
    }
	
	/**
	 * 新建文件夹
	 * @param source
	 * @param model
	 * @return
	 */
	@RequestMapping("mkdirCommit")
	@ResponseBody
    public Callback mkdirCommit(Source source,Model model) {
		try {
			File file=new File(source.getParentPath()+File.separator+source.getName());
			file.mkdir();
	    	  return success();
		} catch (Exception e) {
			  return error();
		}
   	  
    }
	
	/**
	 * 删除
	 * @param tabId
	 * @param source
	 * @param model
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
    public Callback delete(Source source,Model model) {
		try {
			  source.setAbsolutePath(URLDecoder.decode(source.getAbsolutePath(), "UTF-8"));
			  File file=new File(source.getAbsolutePath());
			  FileUtil fileUtil=new FileUtil();
			  fileUtil.deleteAllFilesOfDir(file);
	    	  return success();
		} catch (Exception e) {
			return error();
		}
    }
	 


}
