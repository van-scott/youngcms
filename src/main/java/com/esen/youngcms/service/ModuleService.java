package com.esen.youngcms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.Module;
import com.esen.youngcms.dao.ModuleMapper;

@Service
public class ModuleService extends ServiceImpl<ModuleMapper, Module> {

	@Override
	public boolean insertOrUpdate(Module bean) {
		try {
			if (StringUtils.isBlank(bean.getAuthorize())) {
				bean.setAuthorize(getAuthorizeString(bean.getHref(),4));
			}
			super.insertOrUpdate(bean);
			if (bean.getFunctions() != null) {
				String[] functions = StringUtils.split(bean.getFunctions(), ",");
				for (String function : functions) {
					if (StringUtils.isNotBlank(function)) {
						Module module = new Module();
						switch (Integer.valueOf(function)) {
						case 1: // 新增
							module.setpId(bean.getId());
							module.setName("页面");
							module.setAuthorize(getAuthorizeString(bean.getHref(),1));
							module.setHref(getUrlString(bean.getHref(), 1));
							module.setModuleType(12);
							module.setSort(bean.getSort()+1);
							break;
						case 2: // 修改
							module.setpId(bean.getId());
							module.setName("新增修改");
							module.setAuthorize(getAuthorizeString(bean.getHref(),2));
							module.setHref(getUrlString(bean.getHref(), 2));
							module.setModuleType(12);
							module.setSort(bean.getSort()+1);
							break;
						case 3: // 删除
							module.setpId(bean.getId());
							module.setName("删除");
							module.setAuthorize(getAuthorizeString(bean.getHref(),3));
							module.setHref(getUrlString(bean.getHref(), 3));
							module.setModuleType(12);
							module.setSort(bean.getSort()+1);
							break;
						default:
							break;
						}
						super.insertOrUpdate(module);
					}
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	private String getAuthorizeString(String  url,Integer type){
		String simpleUrl=url;
		if(url.indexOf("?")!=-1){
			   simpleUrl=url.substring(0,url.lastIndexOf("?"));
		}
		String mainUrl=simpleUrl;
		if(url.indexOf("/")!=-1){
			   mainUrl=simpleUrl.substring(0, url.lastIndexOf("/"));
		}
		String authorizeString="";
		if(type==1){
			authorizeString=mainUrl.replaceAll("/", ":")+":form";
		}else if(type==2){
			authorizeString=mainUrl.replaceAll("/", ":")+":addOrUpdate";
		}else if(type==3){
			authorizeString=mainUrl.replaceAll("/", ":")+":delete";
		}else{
			authorizeString=simpleUrl.replaceAll("/", ":");
		}
		return authorizeString.substring(1, authorizeString.length());
	}
	
	private String getUrlString(String  url,Integer type){
		String simpleUrl=url;
		if(url.indexOf("?")!=-1){
			   simpleUrl=url.substring(0,url.lastIndexOf("?"));
		}
		String mainUrl=simpleUrl.substring(0, url.lastIndexOf("/"));
		if(type==1){
			return mainUrl+"/form";
		}else if(type==2){
			return mainUrl+"/addOrUpdate";
		}else{
			return mainUrl+"/delete";
		}
	}
}
