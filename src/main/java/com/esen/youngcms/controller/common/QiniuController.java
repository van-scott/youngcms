package com.esen.youngcms.controller.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esen.youngcms.core.utils.qiniu.Base64Coder;
import com.esen.youngcms.core.utils.qiniu.Config;
import com.esen.youngcms.core.utils.qiniu.ListItem;
import com.esen.youngcms.core.utils.qiniu.ListPrefixRet;
import com.esen.youngcms.core.utils.qiniu.Mac;
import com.esen.youngcms.core.utils.qiniu.RSFClient;
import com.esen.youngcms.core.utils.qiniu.Uptoken;

/**
 * QiNiuController
 * 
 * @author fumiao
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "/common/qiniu/")
public class QiniuController {
	
	
    @RequestMapping(value = "getToken")
    @ResponseBody
    public String findate(HttpServletRequest request) {
    	String dir=request.getParameter("dir");
    	String token="";
		try {
			token = Uptoken.makeUptoken(dir);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return token;
    }
    
    @RequestMapping(value = "callback")
    public void callback(HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	try {
    		request.setCharacterEncoding("utf-8");
        	response.setCharacterEncoding("utf-8");
        	String upload_ret = request.getParameter("upload_ret");
        	JSONObject callback =JSON.parseObject(Base64Coder.decode(upload_ret));
        	JSONObject json = new JSONObject();
        	if (callback.containsKey("error")) {
        		json.put("state", callback.get("error"));
        	} else {
        		json.put("original", callback.get("name"));
        		json.put("url", callback.get("path").toString()+callback.get("key").toString());
        		json.put("state", "SUCCESS");
        	}
        	response.getWriter().write(json.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
    	
    }
    
    @RequestMapping(value = "manager")
    public void manager(HttpServletRequest request,HttpServletResponse response, Model model) {
    	
    	try {
    		String marker=request.getParameter("marker");
            String bucketName=request.getParameter("bucketName");
            String path=request.getParameter("path");
        	Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        	RSFClient client = new RSFClient(mac);
        	ListPrefixRet list = client.listPrifix(bucketName, "",marker, 20);
        	StringBuffer sb = new StringBuffer();
        	for (ListItem item : list.results) {
        		sb.append(path);
        		sb.append("/");
        		sb.append(item.key);
        		sb.append("ue_separate_ue");
        	}
        	String imgStr = sb.toString();
        	if (imgStr != "") {
        		imgStr = imgStr
        				.substring(0, imgStr.lastIndexOf("ue_separate_ue"))
        				.replace(File.separator, "/").trim();
        	}
        	JSONObject json = new JSONObject();
        	json.put("imgStr", imgStr);
        	json.put("marker",list.marker);
        	response.getWriter().write(json.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
    }
}
