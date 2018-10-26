package com.esen.youngcms.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.FriendLink;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.DateUtil;
import com.esen.youngcms.core.utils.ListTypeParameter;
import com.esen.youngcms.service.FriendLinkService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/friendLink/")
public class FriendLinkController extends AdminBaseController{

     @Autowired
     private FriendLinkService friendLinkService;

     @RequestMapping("list")
     public String list(FriendLink bean,Model model) {
        Page<FriendLink> page=friendLinkService.selectPage(getPage(), null);
        model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return PREFIX+"friendLink/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(FriendLink bean) {
       try {
	     friendLinkService.deleteById(bean.getId());
	     return success();
		 } catch (Exception e) {
			return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(FriendLink bean,Model model) {
		if(bean.getId()!=null){
			bean=friendLinkService.selectById(bean.getId());
		}else{
			bean.setClickNum("0");
			bean.setCreateTime(DateUtil.getTodayTimeString());
		}
		model.addAttribute("FRIEND_LINK_TYPE_LIST",ListTypeParameter.friendLinkTypeList);
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"friendLink/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(FriendLink bean) {
		try {
			friendLinkService.insertOrUpdate(bean);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
}
