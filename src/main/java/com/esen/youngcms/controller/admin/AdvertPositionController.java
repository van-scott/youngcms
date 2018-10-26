package com.esen.youngcms.controller.admin;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.AdvertPosition;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.DateUtil;
import com.esen.youngcms.core.utils.ListTypeParameter;
import com.esen.youngcms.service.AdvertPositionService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/advertPosition/")
public class AdvertPositionController extends AdminBaseController{

     @Autowired
     private AdvertPositionService advertPositionService;

     @RequestMapping("list")
     public String list(AdvertPosition bean,Model model) {
        Page<AdvertPosition> page=advertPositionService.selectPage(getPage(),null);
        model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return PREFIX+"advertPosition/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(AdvertPosition bean) {
       try {
		     advertPositionService.deleteById(bean.getId());
		     return  success();
		 } catch (Exception e) {
			 return  error();
		}
	 }

	 @RequestMapping("form")
	 public String form(AdvertPosition bean,Model model) {
		if(bean.getId()!=null){
			bean=advertPositionService.selectById(bean.getId());
		}else{
			bean.setCreateTime(DateUtil.getTodayTimeString());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		model.addAttribute("ADVERT_POSITION_TYPE_LIST", ListTypeParameter.advertPositionTypeList);
		model.addAttribute("IS_OPEN", ListTypeParameter.yesOrNo);
		return PREFIX+"advertPosition/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(AdvertPosition bean,HttpServletRequest request) {
		try {
			advertPositionService.insertOrUpdate(bean);
			return  success();
		} catch (Exception e) {
			return  error();
		}
	}
}
