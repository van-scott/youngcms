package com.esen.youngcms.controller.admin;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.Module;
import com.esen.youngcms.bean.Role;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.ListTypeParameter;
import com.esen.youngcms.service.ModuleService;
import com.esen.youngcms.service.RoleServiceCms;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/module/")
public class ModuleController extends AdminBaseController{

     @Autowired
     private ModuleService moduleService;
     @Autowired
     private RoleServiceCms roleService;
     
     @RequestMapping("simpleNodes")
     @ResponseBody
     public List<Module> simpleNodes(@RequestParam(value="roleId",required=false)String roleId,@RequestParam(value="open",required=false)Boolean open,Module bean) {
    	 Role role=null;
    	 if(StringUtils.isNotBlank(roleId)){
 			  role=roleService.selectById(Integer.valueOf(roleId));
 		 }
    	 List<Module> list=moduleService.selectList(null);
    		 for(int i=0;i<list.size();i++){
    			 Module module2=list.get(i);
    			 if(open!=null && open==true){
    				 module2.setOpen(true);
    			 }
    			 if(module2.getpId()!=null){
    				isCheck(role,module2);
    			 }else{
    				isCheck(role,module2);
    			 }
    		 }
        return list;
     }
     
     private void isCheck(Role role, Module module) {
    	 if(role!=null){
    		 String[] ids=role.getModuleIds().split(",");
    		 Boolean b=false;
    		 for(int i=0;i<ids.length;i++){
    			 if((module.getId().toString()).equals(ids[i])){
    				 b=true;
    			 }
    		 }
    		 if(b){
    			 module.setChecked(true);
    		 }
    	 }
	}

	@RequestMapping("list")
     public String list(Module bean,Model model) {
		EntityWrapper<Module> ew=new EntityWrapper<>();
		ew.orderBy("sort",true);
    	Page<Module> page=moduleService.selectPage(getPage(), ew);
    	model.addAttribute(DEFAULT_PAGE_DATA, getPageData(page, bean));
        return PREFIX+"module/list";
     }

     @RequestMapping("dataPage")
     public String dataPage(Module bean,Model model) {
    	EntityWrapper<Module> ew=new EntityWrapper<>();
 		ew.orderBy("sort",true);
        Page<Module> page=moduleService.selectPage(getPage(), ew);
        model.addAttribute(DEFAULT_PAGE_DATA, getPageData(page, bean));
        return PREFIX+"module/dataPage";
     }
     
     @RequestMapping("treePage")
     public String treePage(Module bean,Model model) {
    	EntityWrapper<Module> ew=new EntityWrapper<>();
  		ew.orderBy("sort",true);
    	List<Module> list=moduleService.selectList(ew);
    	for (Module module : list) {
    		 module.setChildren(getChildren(module.getId(),list));
    	}
        model.addAttribute(DEFAULT_PAGE_DATA, list);
        return PREFIX+"module/treePage";
     }
     
     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(Module bean) {
         try {
	         moduleService.deleteById(bean.getId());
	         return success();
		 } catch (Exception e) {
			 return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(Module bean,Model model) {
		if(bean.getId()!=null){
			bean=moduleService.selectById(bean.getId());
			if(bean.getpId()!=null){
				Module pModule=moduleService.selectById(bean.getpId());
				bean.setpName(pModule.getName());
				bean.setpId(pModule.getId());
			}
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		model.addAttribute("MODULE_TYPE_LIST", ListTypeParameter.moduleTypeList);
		return PREFIX+"module/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(Module module) {
		try {
			moduleService.insertOrUpdate(module);
			return success();
		} catch (Exception e) {
			System.out.println(e.toString());
			return error();
		}
	}
	
	private List<Module> getChildren(Integer id, List<Module> modules) {
		List<Module> moduleList=new ArrayList<Module>();
		for (Module module : modules) {
			if(module.getpId() !=null && module.getpId().toString().equals(id.toString())){
				moduleList.add(module);
			}
		}
		return moduleList;
	}
}
