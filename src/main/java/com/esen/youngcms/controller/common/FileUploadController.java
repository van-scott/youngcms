package com.esen.youngcms.controller.common;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.esen.youngcms.core.utils.DateUtil;
import com.esen.youngcms.vo.Callback;

import net.coobird.thumbnailator.Thumbnails;


@Controller
public class FileUploadController
{
	@Autowired
	private ServletContext servletContext;
	
	//密钥配置
	private Auth auth = Auth.create("4kP-Gd9qIxsULtngQKZofz08-Rx6NlUVpOu4gPoR","G4r2TaQdsuuXuiZtIjru9M6euQdLbRm54WzjFj2N");
	//创建上传对象
	private UploadManager uploadManager = new UploadManager();
	
	@RequestMapping(value="/common/fileUpload.do")
	public void jsUpload(Model model,@RequestParam MultipartFile[] myfiles,HttpServletRequest request,HttpServletResponse response)
	{
		 StringBuffer filePath=new StringBuffer();
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		 MultiValueMap<String, MultipartFile> multiValueMap=multipartRequest.getMultiFileMap();
	     for (Entry<String, List<MultipartFile>> entry: multiValueMap.entrySet()) {
	    	  List<MultipartFile>  multipartFiles=entry.getValue();
	    	  for(MultipartFile multipartFile:multipartFiles){
	    		  if(multipartFile.isEmpty()){  
		                System.out.println("未选择文件");  
		            }else{  
		                try {
		                	String type=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
				            String fileName=System.currentTimeMillis()+Math.round(10)+type;
							uploadManager.put(multipartFile.getBytes(),fileName,auth.uploadToken("fumiao"));
							filePath.append("http://7u2hth.com1.z0.glb.clouddn.com/").append(fileName).append(";");
						} catch (Exception e) {
							e.printStackTrace();
						} 
		            }   
	    	  }
		}
		try {
			 response.setCharacterEncoding("UTF-8");
			 Writer out = response.getWriter();
			 String res = "{ error:'0', msg:'成功',imgurl:'" + filePath.substring(0, filePath.length()-1) + "'}";
			 out.write(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
	
	@RequestMapping(value="/common/imgCut.do")
	@ResponseBody
	public Callback imgCut(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		Callback callback=new Callback(true,"成功");
		String filePath=request.getParameter("filePath");
		String x=request.getParameter("x");
		String y=request.getParameter("y");
		String w=request.getParameter("w");
		String h=request.getParameter("h");
		try {
			String path=servletContext.getRealPath("/")+filePath;
			String fileName=filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
			String toPath=servletContext.getRealPath("/upload")+"/"+DateUtil.getTodayString();
			if(!new File(servletContext.getRealPath("/upload")).exists()){
				new File(servletContext.getRealPath("/upload")).mkdir();
			}
			if(!new File(servletContext.getRealPath("/upload")+"/"+DateUtil.getTodayString()).exists()){
				new File(servletContext.getRealPath("/upload")+"/"+DateUtil.getTodayString()).mkdir();
			}
			Thumbnails.of(new File(path))
			.sourceRegion(Integer.valueOf(x),Integer.valueOf(y),Integer.valueOf(w),Integer.valueOf(h))
			.size(Integer.valueOf(w),Integer.valueOf(h)).keepAspectRatio(false)
			.toFile( new File(toPath,fileName));
			callback.setResult("/upload/"+DateUtil.getTodayString()+"/"+fileName+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return callback;
    }
	
}
	
