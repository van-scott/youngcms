package com.esen.youngcms.controller.admin;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.CmsModel;
import com.esen.youngcms.bean.ExtendField;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.DateUtil;
import com.esen.youngcms.core.utils.DictUtil;
import com.esen.youngcms.core.utils.ListTypeParameter;
import com.esen.youngcms.service.CmsModelService;
import com.esen.youngcms.service.ExtendFieldService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/model/")
public class CmsModelController extends AdminBaseController{

     @Autowired
     private CmsModelService cmsModelService;
     @Autowired
     private ExtendFieldService extendFieldService;

     @RequestMapping("list")
     public String list(CmsModel bean,Model model) {
        Page<CmsModel> page=cmsModelService.selectPage(getPage(), null);
        model.addAttribute(DEFAULT_PAGE_DATA, getPageData(page, bean));
        return PREFIX+"model/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(CmsModel bean,HttpServletRequest request) {
       try {
    	   cmsModelService.deleteById(bean.getId());
		    return success();
		 } catch (Exception e) {
			return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(CmsModel bean,Model model) {
		List<ExtendField> list=new ArrayList<>();
		if(bean.getId()!=null){
			bean=cmsModelService.selectById(bean.getId());
			Map<String, Object> map=new HashMap<>();
			map.put("model_type", DictUtil.getIdByNameAndEnName("extendModelType","栏目模型"));
			map.put("model_id", bean.getId());
			list=extendFieldService.selectByMap(map);
		}else{
			bean.setHasContent(DictUtil.getIdByNameAndEnName("hasContent", "是"));
			bean.setHasGroupImages(DictUtil.getIdByNameAndEnName("hasGroupImages", "否"));
			bean.setHasVedio(DictUtil.getIdByNameAndEnName("hasVedio", "否"));
			bean.setHasOptions(DictUtil.getIdByNameAndEnName("hasOptions", "否"));
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		model.addAttribute("YES_OR_NO", ListTypeParameter.yesOrNo);
		model.addAttribute("extendFieldList",list);
		model.addAttribute("extendFieldTypeList",ListTypeParameter.extendFieldTypeList);
		model.addAttribute("extendFieldTypeListStr",JSONArray.toJSONString(ListTypeParameter.extendFieldTypeList));
		return PREFIX+"model/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(CmsModel bean) {
		try {
			String[] fieldCode=getRequest().getParameterValues("fieldCode");
			String[] fieldName=getRequest().getParameterValues("fieldName");
			String[] fieldType=getRequest().getParameterValues("fieldType");
			String[] isMust=getRequest().getParameterValues("isMust");
			String[] defaultValue=getRequest().getParameterValues("defaultValue");
			String[] isSingleRow=getRequest().getParameterValues("isSingleRow");
			String[] options=getRequest().getParameterValues("options");
			String[] description=getRequest().getParameterValues("description");
			bean.setCreateTime(DateUtil.dateToStr(new Date(), 12));
			Map<String, Object> map=new HashMap<>();
			map.put("fieldCode", fieldCode);
			map.put("fieldName", fieldName);
			map.put("fieldType", fieldType);
			map.put("isMust", isMust);
			map.put("defaultValue", defaultValue);
			map.put("isSingleRow", isSingleRow);
			map.put("options", options);
			map.put("description", description);
			cmsModelService.insertOrUpdate(bean,map);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
}
