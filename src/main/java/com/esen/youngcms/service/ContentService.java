package com.esen.youngcms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.Channel;
import com.esen.youngcms.bean.CmsModel;
import com.esen.youngcms.bean.Content;
import com.esen.youngcms.bean.ContentImage;
import com.esen.youngcms.bean.ContentOption;
import com.esen.youngcms.bean.ContentText;
import com.esen.youngcms.bean.ContentVideo;
import com.esen.youngcms.bean.ExtendField;
import com.esen.youngcms.bean.ExtendFieldValue;
import com.esen.youngcms.bean.FlowFace;
import com.esen.youngcms.core.utils.DictUtil;
import com.esen.youngcms.dao.CmsModelMapper;
import com.esen.youngcms.dao.ContentImageMapper;
import com.esen.youngcms.dao.ContentMapper;
import com.esen.youngcms.dao.ContentOptionMapper;
import com.esen.youngcms.dao.ContentTextMapper;
import com.esen.youngcms.dao.ContentVideoMapper;
import com.esen.youngcms.dao.ExtendFieldMapper;
import com.esen.youngcms.dao.ExtendFieldValueMapper;
@Service
public class ContentService extends ServiceImpl<ContentMapper,Content> {

	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private CmsModelMapper cmsModelMapper;
	@Autowired
	private ContentTextMapper contentTextMapper;
	@Autowired
	private ContentImageMapper contentImageMapper;
	@Autowired
	private ContentOptionMapper contentOptionMapper;
	@Autowired
	private ContentVideoMapper contentVideoMapper;
	@Autowired
	private ExtendFieldMapper extendFieldMapper;
	@Autowired
	private ExtendFieldValueMapper extendFieldValueMapper;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private FlowFaceService flowFaceService;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertOrUpdate(Content content,HttpServletRequest request){
		try {
			if(content.getId()!=null){
				contentMapper.updateById(content);
			}else{
				Channel channel=channelService.selectById(content.getChannelId());
				if(channel.getFlowId()!=null){
					Wrapper<FlowFace> ew=new EntityWrapper<>();
					ew.where("flow_id={0}", channel.getFlowId());
					ew.orderBy("sort", true);
					FlowFace flowFace=flowFaceService.selectOne(ew);
					content.setFlowId(flowFace.getFlowId());
					content.setStatus(flowFace.getSort());
				}else{
					content.setStatus(0);
				}
				contentMapper.insert(content);
			}
            /**
             * 保存扩展字段开始			
             */
			Map<String, Object> params = new HashMap<>();
			params.put("model_type", DictUtil.getIdByNameAndEnName("extendModelType", "栏目模型"));
			params.put("model_id", content.getModelId());
			List<ExtendField> list = extendFieldMapper.selectByMap(params);
			Map<String, Object> fieldValue=new HashMap<>();
			for(int i=0;i<list.size();i++){
				ExtendField extendField=list.get(i);
				String value="";
				if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "复选框")){
					String[] checkbox= request.getParameterValues(extendField.getFieldCode());
					for(int j =0;j<checkbox.length;j++)
					{  
						value+=checkbox[j]+",";  
					}  
					if(value.length()>0){
						value=value.substring(0,value.length()-1);   //截取掉最后一个逗号 
					}
				}else{
					value=request.getParameter(extendField.getFieldCode());
				}
				fieldValue.put(extendField.getFieldCode(), value);
			}
			params.clear();
			params.put("model_type", DictUtil.getIdByNameAndEnName("extendModelType", "内容模型"));
			params.put("model_id", content.getId());
			extendFieldValueMapper.deleteByMap(params);
			ExtendFieldValue extendFieldValue=new ExtendFieldValue();
			extendFieldValue.setModelType(DictUtil.getIdByNameAndEnName("extendModelType", "内容模型"));
			extendFieldValue.setModelId(content.getId());
			extendFieldValue.setData(JSON.toJSONString(fieldValue));
			extendFieldValue.setStatus(0);
			extendFieldValue.setCreateTime(new Date());
			extendFieldValueMapper.insert(extendFieldValue);
			/**
             * 保存扩展字段结束			
             */
			CmsModel cmsModel=cmsModelMapper.selectById(content.getModelId());
			if(cmsModel.getHasContent()==DictUtil.getIdByNameAndEnName("hasContent", "是")){
				EntityWrapper<ContentText> ew=new EntityWrapper<>();
				ew.where("content_id={0}", content.getId());
				contentTextMapper.delete(ew);
				String contentT=request.getParameter("contentText");
				ContentText contentText=new ContentText();
				contentText.setText(contentT);
				contentText.setContentId(content.getId());
				contentTextMapper.insert(contentText);
			}
			if(cmsModel.getHasGroupImages()==DictUtil.getIdByNameAndEnName("hasGroupImages", "是")){
				EntityWrapper<ContentImage> ew=new EntityWrapper<>();
				ew.where("content_id={0}", content.getId());
				contentImageMapper.delete(ew);
				String imagePathAll=request.getParameter("imagePathAll");
				String imageNameAll=request.getParameter("imageNameAll");
				String[] imagePath=StringUtils.split(imagePathAll, ";");
				String[] imageName=StringUtils.split(imageNameAll, ";");
				for(int i=0;i<imagePath.length;i++){
					if(StringUtils.isNotBlank(imagePath[i])){
						ContentImage contentImage=new ContentImage();
						contentImage.setClickNum(0);
						contentImage.setTitle(imageName[i]);
						contentImage.setImageUrl(imagePath[i]);
						contentImage.setContentId(content.getId());
						contentImageMapper.insert(contentImage);
					}
				}
			}
			if(cmsModel.getHasVedio()==DictUtil.getIdByNameAndEnName("hasVedio", "是")){
				EntityWrapper<ContentVideo> ew=new EntityWrapper<>();
				ew.where("content_id={0}", content.getId());
				contentVideoMapper.delete(ew);
				String vedioUrl=request.getParameter("vedioUrl");
				ContentVideo contentVideo=new ContentVideo();
				contentVideo.setVideoUrl(vedioUrl);
				contentVideo.setContentId(content.getId());
				contentVideoMapper.insert(contentVideo);
			}
			if(cmsModel.getHasOptions()==DictUtil.getIdByNameAndEnName("hasOptions", "是")){
				EntityWrapper<ContentOption> ew=new EntityWrapper<>();
				ew.where("content_id={0}", content.getId());
				contentOptionMapper.delete(ew);
				String optionPathAll=request.getParameter("optionPathAll");
				String optionNameAll=request.getParameter("optionNameAll");
				String[] optionPath=StringUtils.split(optionPathAll, ";");
				String[] optionName=StringUtils.split(optionNameAll, ";");
				for(int i=0;i<optionPath.length;i++){
					if(StringUtils.isNotBlank(optionPath[i])){
						ContentOption contentOption=new ContentOption();
						contentOption.setClickNum(0);
						contentOption.setTitle(optionPath[i]);
						contentOption.setOptionUrl(optionName[i]);
						contentOption.setContentId(content.getId());
						contentOptionMapper.insert(contentOption);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public Page<Content> selectPageRecords(Page<Content> page,  Wrapper<Content> wrapper) {
		SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(contentMapper.selectPageRecords(page, wrapper));
        return page;
	}
	
}
