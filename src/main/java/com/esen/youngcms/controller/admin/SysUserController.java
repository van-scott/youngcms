package com.esen.youngcms.controller.admin;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.SysUser;
import com.esen.youngcms.bean.SysUserRole;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.MD5;
import com.esen.youngcms.core.utils.SessionKey;
import com.esen.youngcms.service.RoleServiceCms;
import com.esen.youngcms.service.SysUserRoleService;
import com.esen.youngcms.service.SysUserService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/sysUser/")
public class SysUserController extends AdminBaseController{

     @Autowired
     private SysUserService sysUserService;
     @Autowired
     private SysUserRoleService  sysUserRoleService;
     @Autowired
     private RoleServiceCms roleService;

     @RequestMapping("list")
     public String list(SysUser bean,Model model) {
    	Page<SysUser> page=sysUserService.selectPage(getPage(), null);
    	model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return PREFIX+"sysUser/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(SysUser bean) {
       try {
	     sysUserService.deleteById(bean.getId());
	     return success();
		 } catch (Exception e) {
			 return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(SysUser bean,Model model) {
		 List<SysUserRole> userRoleList=new ArrayList<>();
		if(bean.getId()!=null){
			SysUserRole sysUserRole=new SysUserRole();
			sysUserRole.setSysUserId(bean.getId());
			userRoleList=sysUserRoleService.selectList(new EntityWrapper<>(sysUserRole));
			bean=sysUserService.selectById(bean.getId());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		model.addAttribute("roleList", roleService.selectList(null));
		model.addAttribute("userRoleList", userRoleList);
		return PREFIX+"sysUser/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(@RequestParam("roleIds")String roleIds,SysUser bean) {
		try {
			if(StringUtils.isNotBlank(bean.getPassword())){
				MD5 md5=new MD5();
				bean.setPassword(md5.getMD5ofStr(bean.getPassword()));
			}else{
				String old_password=getPara("old_password");
				bean.setPassword(old_password);
			}
			sysUserService.addOrUpdate(bean, roleIds);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
	
	@RequestMapping("update")
	 public String update(SysUser bean,Model model) {
		Subject currentUser = SecurityUtils.getSubject();  
		SysUser sysUser=(SysUser) currentUser.getSession(true).getAttribute(SessionKey.SYS_USER);
		bean=sysUserService.selectById(sysUser.getId());
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"sysUser/update";
	 }
	
	@RequestMapping("editPwd")
	 public String editPwd(SysUser bean,Model model) {
		Subject currentUser = SecurityUtils.getSubject();  
		SysUser sysUser=(SysUser) currentUser.getSession(true).getAttribute(SessionKey.SYS_USER);
		bean=sysUserService.selectById(sysUser.getId());
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"sysUser/editPwd";
	 }
}
