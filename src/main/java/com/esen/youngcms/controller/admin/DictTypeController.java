package com.esen.youngcms.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.DictType;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.DictTypeService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/dictType/")
public class DictTypeController extends AdminBaseController{

     @Autowired
     private DictTypeService dictTypeService;

     @RequestMapping("list")
     public String list(DictType bean,Model model) {
        Page<DictType> page=dictTypeService.selectPage(getPage());
        model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return "admin/dictType/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(DictType bean) {
       try {
	     dictTypeService.deleteById(bean.getId());
	     return success();
		 } catch (Exception e) {
			return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(DictType bean,Model model) {
		if(bean.getId()!=null){
			bean=dictTypeService.selectById(bean.getId());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return "admin/dictType/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(DictType bean) {
		try {
			dictTypeService.insertOrUpdate(bean);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
}
