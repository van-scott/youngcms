package com.esen.youngcms.controller.admin;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.Dict;
import com.esen.youngcms.bean.DictType;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.DictService;
import com.esen.youngcms.service.DictTypeService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/dict/")
public class DictController extends AdminBaseController{

     @Autowired
     private DictService dictService;
     @Autowired
     private DictTypeService dictTypeService;

     @RequestMapping("list")
     public String list(Dict bean,Model model) {
        Page<Dict> page=dictService.selectPageRecords(getPage(),new EntityWrapper<>());
        model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return "admin/dict/list";
     }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(Dict bean,HttpServletRequest request) {
       try {
		    dictService.deleteById(bean.getId());
		    return success();
		 } catch (Exception e) {
			return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(Dict bean,Model model) {
		List<DictType> list=dictTypeService.selectList(null);
		if(bean.getId()!=null){
			bean=dictService.selectById(bean.getId());
		}
		model.addAttribute("DICT_TYPE_LIST",list);
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return "admin/dict/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(Dict dict,HttpServletRequest request) {
		try {
			String dict_type_id=request.getParameter("dict_type_id");
			if(StringUtils.isNotBlank(dict_type_id)){
				dict.setDictTypeId(Integer.valueOf(dict_type_id));
			}
			dictService.insertOrUpdate(dict);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
}
