package com.esen.youngcms.controller.admin;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.Role;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.RoleServiceCms;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/role/")
public class RoleController extends AdminBaseController{

     @Autowired
     private RoleServiceCms roleService;

     @RequestMapping("list")
     public String list(Role bean,Model model) {
        Page<Role> page=roleService.selectPage(getPage(), null);
        model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return PREFIX+"role/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(Role bean,HttpServletRequest request) {
       try {
		     roleService.deleteById(bean.getId());
		     return success();
		 } catch (Exception e) {
			 return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(Role bean,Model model) {
		if(bean.getId()!=null){
			bean=roleService.selectById(bean.getId());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"role/form";
	 }
	 

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(Role role) {
		try {
			roleService.insertOrUpdate(role);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
	
	@RequestMapping("authorizeForm")
	public String authorizeForm(Role bean,Model model) {
		bean=roleService.selectById(bean.getId());
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"role/authorizeForm";
	 }
	
	@RequestMapping("authorize")
	@ResponseBody
	public Callback authorize(Role bean,Model model) {
		try {
			roleService.insertOrUpdate(bean);
			return success();
		} catch (Exception e) {
			return error();
		}
	 }
}
