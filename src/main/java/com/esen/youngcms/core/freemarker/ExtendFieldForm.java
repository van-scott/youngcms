package com.esen.youngcms.core.freemarker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.esen.youngcms.bean.ExtendField;
import com.esen.youngcms.core.utils.DictUtil;
import com.esen.youngcms.service.ExtendFieldService;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 下面篇文章标题
 * 
 * @author fumiao-pc
 *
 */
@Repository
public class ExtendFieldForm implements TemplateMethodModel {

	@Autowired
	private ExtendFieldService extendFieldService;
	

	@Override
	public Object exec(List arguments) throws TemplateModelException {
		
		String modelType = (String) arguments.get(0); // 扩展模型类型
		String modelId = (String) arguments.get(1); // 扩展模型ID
		String fieldValueStr = (String) arguments.get(2); // 扩展模型ID
		JSONObject fieldValue=JSONObject.parseObject(fieldValueStr);
		
		Map<String, Object> map = new HashMap<>();
		map.put("model_type", modelType);
		map.put("model_id", modelId);
		List<ExtendField> list = extendFieldService.selectByMap(map);
		
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			ExtendField extendField = list.get(i);
			if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "字符串文本")){
				String clazz="";
				String pclazz="";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
					str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+fieldValue.getString(extendField.getFieldCode())+"' class='"+clazz+"'  />";
				}else{
					str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+extendField.getDefaultValue()+"' class='"+clazz+"'  />";
				}
				str += "</p>";
			}else if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "整数文本")){
				String clazz="";
				String pclazz="";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
			       str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+fieldValue.getString(extendField.getFieldCode())+"' class='"+clazz+"'/>";
				}else{
					str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+extendField.getDefaultValue()+"' class='"+clazz+"'/>";
				}
			    str += "</p>";
			}else if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "浮点数文本")){
				String clazz="";
				String pclazz="";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
				    str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+fieldValue.getString(extendField.getFieldCode())+"' class='"+clazz+"' />";
				}else{
					str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+extendField.getDefaultValue()+"' class='"+clazz+"' />";
				}
				str += "</p>";
			}else if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "文本域")){
				String clazz="";
				String pclazz="";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
				   str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+fieldValue.getString(extendField.getFieldCode())+"' class='"+clazz+"' />";
				}else{
				   str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+extendField.getDefaultValue()+"' class='"+clazz+"' />";
				}
				str += "</p>";
			}else if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "日期")){
				String clazz="date";
				String pclazz="";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
					str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+fieldValue.getString(extendField.getFieldCode())+"' class='"+clazz+"' />";
				}else{
					str += "<input type='text' size='30' name='"+extendField.getFieldCode()+"' value='"+extendField.getDefaultValue()+"' class='"+clazz+"' />";
				}
				str += "<a class='inputDateButton' href='javascript:;'>选择</a>";
				str += "</p>";
			}else if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "图片")){
				String clazz="";
				String pclazz="";
				String valueInputId=extendField.getFieldCode();
				String fileInputId=extendField.getFieldCode()+"File";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
					str += "<input type='text' size='30' id='"+valueInputId+"' name='"+valueInputId+"' value='"+fieldValue.getString(extendField.getFieldCode())+"'  class='"+clazz+"'/>";
				}else{
					str += "<input type='text' size='30' id='"+valueInputId+"' name='"+valueInputId+"' value='"+extendField.getDefaultValue()+"'  class='"+clazz+"'/>";
				}
				str += "<input type='file' id='"+fileInputId+"' name='"+fileInputId+"' style='display: none;' onchange='upload.ajaxFileUpload(\""+fileInputId+"\",\""+valueInputId+"\",\"navTab\");'/>";
				str += "<a class='buttonActive' href='javascript:void(0);' onclick='upload.chooseFile(\""+fileInputId+"\");'><span>上传</span></a>";
				str += "</p>";
			}else if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "附件")){
				String clazz="";
				String pclazz="";
				String valueInputId=extendField.getFieldCode();
				String fileInputId=extendField.getFieldCode()+"File";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
					str += "<input type='text' size='30' id='"+valueInputId+"' name='"+valueInputId+"' value='"+fieldValue.getString(extendField.getFieldCode())+"'  class='"+clazz+"'/>";
				}else{
					str += "<input type='text' size='30' id='"+valueInputId+"' name='"+valueInputId+"' value='"+extendField.getDefaultValue()+"'  class='"+clazz+"'/>";
				}
				str += "<input type='file' id='"+fileInputId+"' name='"+fileInputId+"' style='display: none;' onchange='upload.ajaxFileUpload(\""+fileInputId+"\",\""+valueInputId+"\",\"navTab\");'/>";
				str += "<a class='buttonActive' href='javascript:void(0);' onclick='upload.chooseFile(\""+fileInputId+"\");'><span>上传</span></a>";
				str += "</p>";
			}else if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "下拉列表")){
				String clazz="";
				String pclazz="";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				str += "<select name='"+extendField.getFieldCode()+"' class='"+clazz+"'>";
				String value="";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
					value=fieldValue.getString(extendField.getFieldCode());
				}else{
					value=extendField.getDefaultValue();
				}
				String[] options=StringUtils.split(extendField.getOptions(),",");
				for(int j=0;j<options.length;j++){
					int v=j+1;
					if(StringUtils.isNotBlank(options[j])){
						if((v+"").equals(value)){
							str +="<option value='"+v+"' selected='selected'>"+options[j]+"</option>";
						}else{
							str +="<option value='"+v+"'>"+options[j]+"</option>";
						}
					}
				}
				str += "</select>";
				str += "</p>";
			}else if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "复选框")){
				String clazz="";
				String pclazz="";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				String value="";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
					value=fieldValue.getString(extendField.getFieldCode());
				}else{
					value=extendField.getDefaultValue();
				}
				String[] options=StringUtils.split(extendField.getOptions(),",");
				for(int j=0;j<options.length;j++){
					int v=j+1;
					if(StringUtils.isNotBlank(options[j])){
						 if(value.contains(v+"")){
						    str +="<input type='checkbox' value='"+v+"' name='"+extendField.getFieldCode()+"' class='"+clazz+"' checked='checked' />"+options[j]+"";
						 }else{
						    str +="<input type='checkbox' value='"+v+"' name='"+extendField.getFieldCode()+"' class='"+clazz+"' />"+options[j]+"";
						 }
					}
				}
				str += "</p>";
			}else if(extendField.getFieldType()==DictUtil.getIdByNameAndEnName("extendFieldType", "单选框")){
				String clazz="";
				String pclazz="";
				if(extendField.getIsMust()==1){
					clazz+=" required";
				}
				if(extendField.getIsSingleRow()==1){
					pclazz+=" nowrap";
				}
				str += "<p class='"+pclazz+"'>";
				str += "<label>"+extendField.getFieldName()+"</label>";
				String value="";
				if(fieldValue!=null && fieldValue.containsKey(extendField.getFieldCode())){
					value=fieldValue.getString(extendField.getFieldCode());
				}else{
					value=extendField.getDefaultValue();
				}
				String[] options=StringUtils.split(extendField.getOptions(),",");
				for(int j=0;j<options.length;j++){
					int v=j+1;
					if(StringUtils.isNotBlank(options[j])){
						if((v+"").equals(value)){
							str +="<input type='radio' value='"+v+"' name='"+extendField.getFieldCode()+"' class='"+clazz+"' checked='checked' />"+options[j]+"";
						}else{
							str +="<input type='radio' value='"+v+"' name='"+extendField.getFieldCode()+"' class='"+clazz+"' />"+options[j]+"";
						}
					}
				}
				str += "</p>";
			}
		}
		return str;
	}

}
