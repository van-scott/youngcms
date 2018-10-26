package com.esen.youngcms.controller.admin;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.SysUserRole;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.SysUserRoleService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/sysUserRole/")
public class SysUserRoleController extends AdminBaseController{

     @Autowired
     private SysUserRoleService sysUserRoleService;

     @RequestMapping("list")
     public String list(SysUserRole bean,Model model) {
        Page<SysUserRole> page=sysUserRoleService.selectPage(getPage(), null);
        model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return PREFIX+"sysUserRole/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(SysUserRole bean) {
       try {
	       sysUserRoleService.deleteById(bean.getId());
	       return success();
		 } catch (Exception e) {
		   return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(SysUserRole bean,Model model) {
		if(bean.getId()!=null){
			bean=sysUserRoleService.selectById(bean.getId());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"sysUserRole/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(SysUserRole bean,HttpServletRequest request) {
		try {
			sysUserRoleService.insertOrUpdate(bean);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
}
