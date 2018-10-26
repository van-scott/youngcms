package com.esen.youngcms.controller.admin;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.Channel;
import com.esen.youngcms.bean.ChannelModel;
import com.esen.youngcms.bean.CmsModel;
import com.esen.youngcms.bean.Content;
import com.esen.youngcms.bean.ContentImage;
import com.esen.youngcms.bean.ContentOption;
import com.esen.youngcms.bean.ContentText;
import com.esen.youngcms.bean.ContentVideo;
import com.esen.youngcms.bean.ExtendFieldValue;
import com.esen.youngcms.bean.FlowFace;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.annotation.OperateLog;
import com.esen.youngcms.core.utils.DateUtil;
import com.esen.youngcms.core.utils.DictUtil;
import com.esen.youngcms.core.utils.ListTypeParameter;
import com.esen.youngcms.core.utils.SessionKey;
import com.esen.youngcms.service.ChannelModelService;
import com.esen.youngcms.service.ChannelService;
import com.esen.youngcms.service.CmsModelService;
import com.esen.youngcms.service.ContentImageService;
import com.esen.youngcms.service.ContentOptionService;
import com.esen.youngcms.service.ContentService;
import com.esen.youngcms.service.ContentTextService;
import com.esen.youngcms.service.ContentVideoService;
import com.esen.youngcms.service.ExtendFieldValueService;
import com.esen.youngcms.service.FlowFaceService;
import com.esen.youngcms.vo.Callback;
@Controller
@RequestMapping("/admin/content/")
public class ContentController extends AdminBaseController{

     @Autowired
     private ContentService contentService;
     @Autowired
     private ChannelService channelService;
     @Autowired
     private ChannelModelService channelModelService;
     @Autowired
     private CmsModelService cmsModelService;
     @Autowired
     private ContentTextService contentTextService;
     @Autowired
     private ContentImageService contentImageService;
     @Autowired
     private ContentOptionService contentOptionService;
     @Autowired
     private ContentVideoService contentVideoService;
     @Autowired
     private ExtendFieldValueService extendFieldValueService;
     @Autowired
     private FlowFaceService flowFaceService;

     @RequestMapping("dataPage")
     public String dataPage(Content content) {
        return PREFIX+"content/dataPage";
     }
     
     @RequestMapping("getChannelModel")
     @ResponseBody
     public Object getChannelModel(Content bean) {
		List<ChannelModel> list=channelModelService.selectByChannelId(bean.getChannelId());
        return list;
     }
     
     @OperateLog(moudleName="内容",optName="列表",description="内容列表")
     @RequestMapping("list")
     public String list(Content bean,Model model) {
    	EntityWrapper<Content> entityWrapper=new EntityWrapper<>();
    	entityWrapper.where("a.channel_id={0}", bean.getChannelId());
    	Page<Content> page=contentService.selectPageRecords(getPage(),entityWrapper);
        model.addAttribute(DEFAULT_PAGE_DATA, getPageData(page,bean));
        return PREFIX+"content/list";
     }
     
     @OperateLog(moudleName="内容",optName="删除",description="内容删除")
	 @RequestMapping("delete")
     @ResponseBody
     public Callback delete(Content bean) {
       try {
	     contentService.deleteById(bean.getId());
	     return  success();
		 } catch (Exception e) {
			return  error();
		}
	 }
     
     @OperateLog(moudleName="内容",optName="审核",description="内容审核")
	 @RequestMapping("nextNode")
     @ResponseBody
     public Callback nextNode(Content bean) {
       try {
    	 Content contnt=contentService.selectById(bean.getId());
    	 Wrapper<FlowFace> ew=new EntityWrapper<>();
		 ew.where("flow_id={0}", contnt.getFlowId());
		 ew.where("sort={0}", contnt.getStatus()+1);
		 FlowFace flowFace=flowFaceService.selectOne(ew);
		 if(flowFace!=null){
			 contnt.setStatus(flowFace.getSort()); 
		 }else{
			 contnt.setStatus(0);
		 }
	     contentService.updateById(contnt);
	     return  success();
		 } catch (Exception e) {
			return  error();
		}
	 }
	 
	 @OperateLog(moudleName="内容",optName="新增或修改页面",description="内容新增或修改页面")
	 @RequestMapping("form")
	 public String form(Content bean,Model model) {
		if(bean.getId()!=null){
			bean=contentService.selectById(bean.getId());
		}else{
			String sysUserName=(String) getSessionAttr(SessionKey.SYS_USER_NAME);
			bean.setIsTop(DictUtil.getIdByNameAndEnName("isTop","否"));
			bean.setIsComment(DictUtil.getIdByNameAndEnName("isComment","是"));
			bean.setWeight(10);
			bean.setCreateUser(sysUserName);
			bean.setCreateTime(DateUtil.dateToStr(new Date(), 12));
		}
		ExtendFieldValue extendFieldValue=new ExtendFieldValue();
		
		Channel channel=channelService.selectById(bean.getChannelId());
		bean.setChannelId(channel.getId());
		bean.setChannelName(channel.getName());
		
		CmsModel cmsModel=cmsModelService.selectById(bean.getModelId());
		bean.setModelId(cmsModel.getId());
		bean.setModelName(cmsModel.getName());
		
		Map<String, Object> params=new HashMap<>();
		params.put("content_id", bean.getId());
		if(bean.getId()!=null){
			extendFieldValue.setModelType(DictUtil.getIdByNameAndEnName("extendModelType", "内容模型"));
			extendFieldValue.setModelId(bean.getId());
			extendFieldValue=extendFieldValueService.selectOne(new EntityWrapper<>(extendFieldValue));
			if(cmsModel.getHasContent()==DictUtil.getIdByNameAndEnName("yesOrNo", "是")){
				List<ContentText> contentText = contentTextService.selectByMap(params);
				if(contentText !=null && contentText.size()>0){
					bean.setContentText(contentText.get(0));
				}
			}
			if(cmsModel.getHasVedio()==DictUtil.getIdByNameAndEnName("yesOrNo", "是")){
				List<ContentVideo> contentVideo=contentVideoService.selectByMap(params);
				if(contentVideo!=null && contentVideo.size()>0){
					bean.setContentVideo(contentVideo.get(0));
				}
			}
			if(cmsModel.getHasGroupImages()==DictUtil.getIdByNameAndEnName("yesOrNo", "是")){
				List<ContentImage> list=contentImageService.selectByMap(params);
				bean.setContentImages(list);
			}
			if(cmsModel.getHasOptions()==DictUtil.getIdByNameAndEnName("yesOrNo", "是")){
				List<ContentOption> list=contentOptionService.selectByMap(params);
				bean.setContentOptions(list);
			}
		}
		model.addAttribute(DEFAULT_PAGE_FORM, bean);
		model.addAttribute("model", cmsModel);
		model.addAttribute("extendFieldValue", extendFieldValue);
		model.addAttribute("YES", DictUtil.getIdByNameAndEnName("yesOrNo", "是"));
		model.addAttribute("YES_OR_NO", ListTypeParameter.yesOrNo);
		model.addAttribute("modelType", DictUtil.getIdByNameAndEnName("extendModelType", "栏目模型"));
		model.addAttribute("templates","");
		return PREFIX+"content/form";
	 }
	 
	@OperateLog(moudleName="内容",optName="新增或修改",description="内容新增或修改")
	@RequestMapping("addOrUpdate")
	@ResponseBody
	public Callback add(Content content) {
		String[] a =getRequest().getParameterValues("items.attachment.attachUrl[]");
		try {
			content.setCreateTime(DateUtil.dateToStr(new Date(), 12));
			contentService.insertOrUpdate(content,getRequest());
			Callback s=success();
		    s.setNavTabId("147");
		    return s;
		} catch (Exception e) {
			return  error();
		}
	}
	
	@RequestMapping("attachSet")
	@ResponseBody
	public void attachSet(@RequestParam(required=false,value="attachUrl")String attachUrl) {
		try {
			String res = "{\"error\":\"0\", \"msg\":\"成功\",\"attachUrl\":\"" + attachUrl + "\"}";
			getResponse().getWriter().write(res);
		} catch (Exception e) {
		}
	}
	
	
	
	
}
