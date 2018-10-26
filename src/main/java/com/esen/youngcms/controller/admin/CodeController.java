package com.esen.youngcms.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.CodeService;
import com.esen.youngcms.vo.Callback;
import com.esen.youngcms.vo.CodeVO;
import com.esen.youngcms.vo.QueryResult;

@Controller
@RequestMapping("/admin/code/")
public class CodeController extends AdminBaseController {
	@Autowired
	private CodeService codeService;
	
	@RequestMapping("list")
	public ModelAndView list(CodeVO codeVO,HttpServletRequest request,ModelMap mode) {
		QueryResult<CodeVO> queryResult=codeService.getBeanList();
		codeVO.setPageDate(queryResult.getQueryResult());
		codeVO.setTotalCount(queryResult.getCount());
		
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("view/admin/code/list");
		mnv.addObject(DEFAULT_PAGE_DATA,codeVO);
		return mnv;
	}
	
	@RequestMapping("create")
	@ResponseBody
	public Callback create(CodeVO codeVO,HttpServletRequest request,ModelMap mode) {
		try {
			codeService.createDao(codeVO.getClazzName());
			codeService.createDaoImpl(codeVO.getClazzName());
			codeService.createService(codeVO.getClazzName());
			codeService.createServiceImpl(codeVO.getClazzName());
			codeService.createController(codeVO.getClazzName());
			codeService.createListView(codeVO.getClazzName());
			codeService.createFormView(codeVO.getClazzName());
			return success();
		} catch (Exception e) {
			return error();
		}
	}

}
