package com.esen.youngcms.controller.admin;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.FlowFace;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.FlowFaceService;
import com.esen.youngcms.service.FlowService;
import com.esen.youngcms.service.RoleServiceCms;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/flowFace/")
public class FlowFaceController extends AdminBaseController{

     @Autowired
     private FlowFaceService flowFaceService;
     @Autowired
     private FlowService flowService;
     @Autowired
     private  RoleServiceCms roleService;

     @RequestMapping("list")
     public String list(FlowFace bean,Model model) {
    	 Page<FlowFace> page=flowFaceService.selectPage(getPage(), new EntityWrapper<>(bean).orderBy("sort"));
    	 model.addAttribute(DEFAULT_PAGE_DATA, getPageData(page, bean));
        return PREFIX+"flowFace/list";
     }
     
     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(FlowFace bean) {
       try {
	     flowFaceService.deleteById(bean.getId());
	     Wrapper<FlowFace> ew=new EntityWrapper<>();
		 ew.where("flow_id={0}", bean.getFlowId());
		 ew.orderBy("sort", true);
		 List<FlowFace> flowFaces=flowFaceService.selectList(ew);
		 for(int i=0;i<flowFaces.size();i++){
			 FlowFace flowFace=flowFaces.get(i);
			 flowFace.setSort(i+1);
			 flowFaceService.insertOrUpdate(flowFace);
		 }
	     return success();
		 } catch (Exception e) {
			 return  error();
		}
	 }

	 @RequestMapping("form")
	 public String form(FlowFace bean,Model model) {
		String[] flowFaceRoleList={};
		if(bean.getId()!=null){
			bean=flowFaceService.selectById(bean.getId());
			if(StringUtils.isNotBlank(bean.getRoleIds())){
				flowFaceRoleList=bean.getRoleIds().split(",");
			}
		}
		model.addAttribute("roleList", roleService.selectList(null));
		model.addAttribute("flowList", flowService.selectList(null));
		model.addAttribute("flowFaceRoleList", flowFaceRoleList);
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		return PREFIX+"flowFace/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(FlowFace bean) {
		try {
			if(bean.getId()==null){
				Wrapper<FlowFace> ew=new EntityWrapper<>();
				ew.where("flow_id={0}", bean.getFlowId());
				ew.orderBy("sort", false);
				FlowFace flowFace=flowFaceService.selectOne(ew);
				if(flowFace!=null){
					bean.setSort(flowFace.getSort()+1);
				}else{
					bean.setSort(1);
				}
			}
			flowFaceService.insertOrUpdate(bean);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
}
