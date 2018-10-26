package com.esen.youngcms.controller.admin;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.Channel;
import com.esen.youngcms.bean.ChannelModel;
import com.esen.youngcms.bean.ChannelTemplate;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.Constants;
import com.esen.youngcms.service.ChannelModelService;
import com.esen.youngcms.service.ChannelService;
import com.esen.youngcms.service.ChannelTemplateService;
import com.esen.youngcms.service.CmsModelService;
import com.esen.youngcms.service.FlowService;
import com.esen.youngcms.vo.Callback;
import com.esen.youngcms.vo.LabelValue;
import com.esen.youngcms.vo.Source;
@Controller
@RequestMapping("/admin/channel/")
public class ChannelController extends AdminBaseController{

     @Autowired
     private ChannelService channelService;
     @Autowired
     private ChannelModelService channelModelService;
     @Autowired
     private ChannelTemplateService channelTemplateService;
     @Autowired
     private CmsModelService cmsModelService;
     @Autowired
     private FlowService flowService;
     
     @RequestMapping("simpleNodes")
     @ResponseBody
     public List<Channel> simpleNodes(Channel bean) {
    	 List<Channel> list=channelService.selectList(null);
    	 List<ChannelModel> channelModels=channelModelService.selectAll();
    		 for(Channel channel:list){
    			 String s="";
    			 for(ChannelModel channelModel:channelModels){
    				 if(channel.getId()==channelModel.getChannelId()){
    					 s+="<o style='color:red'>"+channelModel.getModelName()+"</o>,";
    				 }
    			 }
    			 if(s.length()>0){
    				 channel.setName(channel.getName()+"["+s.substring(0, s.length()-1)+"]");
    			 }else{
    				 channel.setName(channel.getName());
    			 }
    			 channel.setOpen(true);
    		 }
        return list;
     }
     
     @RequestMapping("list")
     public String list(Channel bean,Model model) {
    	Page<Channel> page=channelService.selectPage(getPage(), null);
    	model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return PREFIX+"channel/list";
     }

	@RequestMapping("dataPage")
     public String dataPage(Channel bean) {
        return PREFIX+"channel/dataPage";
     }
	
	@RequestMapping("treePage")
    public String treePage(Channel bean,Model model) {
	EntityWrapper<Channel> ew=new EntityWrapper<>();
	ew.orderBy("sort", true);
   	List<Channel> list=channelService.selectList(ew);
   	List<ChannelModel> channelModels=channelModelService.selectAll();
   	for (Channel channel : list) {
		 String s="";
		 for(ChannelModel channelModel:channelModels){
			 if(channel.getId()==channelModel.getChannelId()){
				 s+="<o style='color:red'>"+channelModel.getModelName()+"</o>,";
			 }
		 }
		 if(s.length()>0){
			 channel.setName(channel.getName()+"["+s.substring(0, s.length()-1)+"]");
		 }else{
			 channel.setName(channel.getName());
		 }
   		channel.setChildren(getChildren(channel.getId(),list));
   	}
       model.addAttribute(DEFAULT_PAGE_DATA, list);
       return PREFIX+"channel/treePage";
    }

     @RequestMapping("delete")
     @ResponseBody
     public Callback delete(Channel bean) {
       try {
	        channelService.deleteById(bean.getId());
		    return success();
		 } catch (Exception e) {
			return error();
		}
	 }

	 @RequestMapping("form")
	 public String form(@RequestParam(value="tabId",required=false)String tabId,Channel bean,Model model) {
		if(bean.getId()!=null){
			bean=channelService.selectById(bean.getId());
		}else{
			bean.setIsNav(2);
			bean.setIsOut(2);
		}
		if(bean.getpId()!=null){
			Channel pChannel=channelService.selectById(bean.getpId());
			bean.setpName(pChannel.getName());
			bean.setpId(pChannel.getId());
		}
		List<ChannelModel> channelModels=new ArrayList<>();
		List<ChannelTemplate> channelTemplates=new ArrayList<>();
		if(bean.getId()!=null){
			channelModels=channelModelService.selectByChannelId(bean.getId());
			channelTemplates=channelTemplateService.selectByChannelId(bean.getId());
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		model.addAttribute("modelList", cmsModelService.selectList(new EntityWrapper<>()));
		model.addAttribute("flowList", flowService.selectList(new EntityWrapper<>()));
		model.addAttribute("channelModelList",channelModels);
		model.addAttribute("channelTemplateList",channelTemplates);
		model.addAttribute("templates", getTemplate());
		model.addAttribute("tabId", tabId);
		return PREFIX+"channel/form";
	 }

	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(Channel bean) {
		try {
			String[] models=getRequest().getParameterValues("model");
			String[] templates=getRequest().getParameterValues("template");
			channelService.insertOrUpdate(bean,models,templates);
			return success();
		} catch (Exception e) {
			return error();
		}
	}
	
	@RequestMapping("chooseTemplate")
    public String dataPage(@RequestParam(value="modelId",required=false)String modelId,Source source,Model model) {
		model.addAttribute("modelId", modelId);
        return PREFIX+"channel/chooseTemplate";
    }
	
	private List<Channel> getChildren(Integer id, List<Channel> channels) {
		List<Channel> channelList=new ArrayList<Channel>();
		for (Channel channel : channels) {
			if(channel.getpId() !=null && channel.getpId().toString().equals(id.toString())){
				channelList.add(channel);
			}
		}
		return channelList;
	}
	
	
	private List<LabelValue> getTemplate(){
		String templatePath=this.getClass().getResource("/tpl/")+Constants.TEMPLATE_FOLDER+"/channel/";
		List<LabelValue> list=new ArrayList<LabelValue>();
		File file=new File(templatePath);
		String[] templates=file.list();
		if(templates!=null){
			for(int i=0;i<templates.length;i++){
				LabelValue labelValue=new LabelValue();
				labelValue.setLabel(Constants.TEMPLATE_FOLDER+"/channel/"+templates[i]);
				labelValue.setValue(Constants.TEMPLATE_FOLDER+"/channel/"+templates[i]);
				list.add(labelValue);
			}
		}
		return list;
	}
}
