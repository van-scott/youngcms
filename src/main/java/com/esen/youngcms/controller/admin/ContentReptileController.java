package com.esen.youngcms.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.ContentReptile;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.ContentReptileService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/contentReptile/")
public class ContentReptileController extends AdminBaseController{

     @Autowired
     private ContentReptileService contentReptileService;
     
     @RequestMapping("list")
     public String list(ContentReptile bean,Model model) {
    	Page<ContentReptile> page=contentReptileService.selectPage(getPage(),null);
        model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return PREFIX+"contentReptile/list";
     }
     
     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(ContentReptile bean) {
       try {
    	   contentReptileService.deleteById(bean.getId());
	       return success();
		 } catch (Exception e) {
			return error();
		}
	 }
     
	 @RequestMapping("form")
	 public String  form(ContentReptile bean,Model model) {
		if(bean.getId()!=null){
			bean=contentReptileService.selectById(bean.getId());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"contentReptile/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(ContentReptile bean) {
		try {
			contentReptileService.insertOrUpdate(bean);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
	
	@RequestMapping("toContent")
	@ResponseBody
	public Callback toContent(@RequestParam("ids")String ids,@RequestParam("channelId")String channelId) {
		try {
			//contentReptileService.toContent(ids,channelId);
			return success();
		} catch (Exception e) {
			return error();
		}
	}


 
}
