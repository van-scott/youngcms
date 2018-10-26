package com.esen.youngcms.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.Site;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.ListTypeParameter;
import com.esen.youngcms.service.SiteService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/site/")
public class SiteController extends AdminBaseController{

     @Autowired
     private SiteService siteService;

     @RequestMapping("list")
     public String list(Site bean,Model model) {
        Page<Site> page=siteService.selectPage(getPage());
        model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return "admin/site/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(Site bean) {
       try {
    	   siteService.deleteById(bean.getId());
	     return success();
		 } catch (Exception e) {
			return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(Site bean,Model model) {
		if(bean.getId()!=null){
			bean=siteService.selectById(bean.getId());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		model.addAttribute("YES_OR_NO", ListTypeParameter.yesOrNo);
		model.addAttribute("PROTOCOL", ListTypeParameter.protocolList);
		model.addAttribute("POSTFIX", ListTypeParameter.postfixList);
		return "admin/site/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(Site bean) {
		try {
			siteService.insertOrUpdate(bean);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
}
