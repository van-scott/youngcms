package com.esen.youngcms.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.Flow;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.FlowService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/flow/")
public class FlowController extends AdminBaseController{

     @Autowired
     private FlowService flowService;

     @RequestMapping("list")
     public String list(Flow bean,Model model) {
    	 Page<Flow> page=flowService.selectPage(getPage(), null);
    	 model.addAttribute(DEFAULT_PAGE_DATA, getPageData(page, bean));
        return PREFIX+"flow/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(Flow bean) {
       try {
    	   flowService.deleteById(bean.getId());
	       return success();
		 } catch (Exception e) {
		   return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(Flow bean,Model model) {
		if(bean.getId()!=null){
			bean=flowService.selectById(bean.getId());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"flow/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(Flow bean) {
		try {
			flowService.insertOrUpdate(bean);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
}
