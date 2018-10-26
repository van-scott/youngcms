package com.esen.youngcms.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.Advert;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.annotation.OperateLog;
import com.esen.youngcms.core.utils.DateUtil;
import com.esen.youngcms.service.AdvertPositionService;
import com.esen.youngcms.service.AdvertService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/advert/")
public class AdvertController extends AdminBaseController{

     @Autowired
     private AdvertService advertService;
     @Autowired
     private AdvertPositionService advertPositionService;
     
     @OperateLog(moudleName="广告",optName="列表",description="广告列表")
     @RequestMapping("list")
     public String list(Advert bean,Model model) {
        Page<Advert> page=advertService.selectPage(getPage(),null);
        model.addAttribute(DEFAULT_PAGE_DATA, getPageData(page, bean));
        return PREFIX+"advert/list";
     }
     
     @OperateLog(moudleName="内容",optName="删除",description="内容删除")
     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(Advert bean) {
       try {
		     advertService.deleteById(bean.getId());
		     return success();
		 } catch (Exception e) {
			 return error();
		}
	 }

     @OperateLog(moudleName="内容",optName="删除",description="内容删除")
	 @RequestMapping("form")
	 public String form(Advert bean,Model model) {
		if(bean.getId()!=null){
			bean=advertService.selectById(bean.getId());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		model.addAttribute("ADVERT_POSITION_LIST", advertPositionService.selectList(null));
		return PREFIX+"advert/form";
	 }

    @OperateLog(moudleName="内容",optName="删除",description="内容删除")
	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(Advert bean) {
		try {
			bean.setClickNum(0);
			bean.setCreateTime(DateUtil.getTodayTimeString());
			advertService.insertOrUpdate(bean);
			return  success();
		} catch (Exception e) {
			return  error();
		}
	}
}
