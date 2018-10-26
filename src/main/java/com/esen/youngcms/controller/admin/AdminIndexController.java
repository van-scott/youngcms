package com.esen.youngcms.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esen.youngcms.bean.Module;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.Constants;
import com.esen.youngcms.core.utils.SessionKey;
import com.esen.youngcms.vo.Menu;

@Controller
@RequestMapping("/admin/")
public class AdminIndexController extends AdminBaseController{
	
	@RequestMapping("index")
	public String list() {
		return PREFIX+"index";
	}
	
	@RequestMapping("left")
	public String left() {
		return PREFIX+"left";
		
	}
	
	@RequestMapping("top")
	public String head(HttpServletRequest request,ModelMap mode) {
		return PREFIX+"top";
	}
	
	@RequestMapping("main")
	public String main(HttpServletRequest request,ModelMap mode) {
		return PREFIX+"main";
		
	}
	
	@RequestMapping("footer")
	public String foot(HttpServletRequest request,ModelMap mode) {
		return PREFIX+"footer";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("getMenuAjax")
	@ResponseBody
	public List<Menu> getFirstMenu(@RequestParam(value="pid",required=false)String pid) {
		List<Menu> menus=new ArrayList<Menu>();
		Subject subject = SecurityUtils.getSubject();  
		List<Module> modules=(List<Module>) subject.getSession(true).getAttribute(SessionKey.SYS_MODULE);
		for (Module module : modules) {
			if(Constants.MODULU_TYPE_MENU.equals(module.getModuleType())){
				if(module.getpId()!=null && module.getpId().toString().equals(pid)){
					Menu menu=new Menu();
					menu.setId(module.getId());
					menu.setName(module.getName());
					menu.setUrl(module.getHref());
					menu.setTabId(module.getTabId());
					menu.setIcon(module.getIcon());
					menu.setIsTree(module.getIsTree());
					menu.setTreeUrl(module.getTreeUrl());
					menu.setExternal(module.getExternal());
					menu.setChildren(getChildren(module.getId(),modules));
					menus.add(menu);
				}
			}
		}
		return menus;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("getMenu")
	public String getModules(@RequestParam(value="pid",required=false)String pid,Model model) {
		List<Menu> menus=new ArrayList<Menu>();
		Subject subject = SecurityUtils.getSubject();  
		List<Module> modules=(List<Module>) subject.getSession(true).getAttribute(SessionKey.SYS_MODULE);
		for (Module module : modules) {
			if(Constants.MODULU_TYPE_MENU.equals(module.getModuleType())){
				if(module.getpId()!=null && module.getpId().toString().equals(pid)){
					Menu menu=new Menu();
					menu.setId(module.getId());
					menu.setName(module.getName());
					menu.setUrl(module.getHref());
					menu.setTabId(module.getTabId());
					menu.setIcon(module.getIcon());
					menu.setIsTree(module.getIsTree());
					menu.setTreeUrl(module.getTreeUrl());
					menu.setExternal(module.getExternal());
					menu.setChildren(getChildren(module.getId(),modules));
					menus.add(menu);
				}
			}
		}
		model.addAttribute("menus", menus);
		return PREFIX+"menu";
	}
	
	private List<Menu> getChildren(Integer id, List<Module> modules) {
		List<Menu> menus=new ArrayList<Menu>();
		for (Module module : modules) {
			if(module.getpId() !=null && id.equals(module.getpId())){
				if(Constants.MODULU_TYPE_MENU.equals(module.getModuleType())){
					Menu menu=new Menu();
					menu.setId(module.getId());
					menu.setName(module.getName());
					menu.setUrl(module.getHref());
					menu.setTabId(module.getTabId());
					menu.setIcon(module.getIcon());
					menu.setExternal(module.getExternal());
					menus.add(menu);
				}
			}
		}
		return menus;
	}
	
}
