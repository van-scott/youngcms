package com.esen.youngcms.controller.admin;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.ReptileRule;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.MessageDate;
import com.esen.youngcms.core.utils.redis.JedisClient;
import com.esen.youngcms.service.ReptileRuleService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/reptileRule/")
public class ReptileRuleController extends AdminBaseController{

     @Autowired
     private ReptileRuleService reptileRuleService;
     @Autowired
     private JedisClient jedisClient;

     @RequestMapping("list")
     public String list(ReptileRule bean,Model model) {
        Page<ReptileRule> page=reptileRuleService.selectPage(getPage(), null);
        model.addAttribute(DEFAULT_PAGE_DATA, getPageData(page, bean));
        return PREFIX+"reptileRule/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(ReptileRule reptileRule) {
       try {
    	    reptileRuleService.selectById(reptileRule.getId());
	        return success();
		 } catch (Exception e) {
			return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(ReptileRule bean,Model model) {
		if(bean.getId()!=null){
			bean=reptileRuleService.selectById(bean.getId());
		}else{
			bean.setCoding("UTF-8");
			bean.setTimeFormat("yyyy-MM-dd HH:mm:ss");
			bean.setNumber(500);
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"reptileRule/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(ReptileRule bean,HttpServletRequest request) {
		try {
			reptileRuleService.insertOrUpdate(bean);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
	
	@RequestMapping("reptile")
	@ResponseBody
	public Callback reptile(ReptileRule bean,HttpServletRequest request) {
		try {
			MessageDate messageDate=new MessageDate();
			messageDate.setCommandName("reptileContentCommand");
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("reptileRuleId", bean.getId());
			messageDate.setParams(map);
			jedisClient.getJedis().lpush(MessageDate.REDIS_KEY,JSON.toJSONString(messageDate));
			return success();
		} catch (Exception e) {
			return error();
		}
	}
}
