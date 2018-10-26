package com.esen.youngcms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.CmsModel;
import com.esen.youngcms.bean.ExtendField;
import com.esen.youngcms.core.utils.DictUtil;
import com.esen.youngcms.dao.CmsModelMapper;
@Service
public class CmsModelService extends ServiceImpl<CmsModelMapper,CmsModel> {
	
	@Autowired
	private ExtendFieldService extendFieldService;

	public void insertOrUpdate(CmsModel bean, Map<String, Object> map) {
		if(bean.getId()!=null){
			super.updateById(bean);
		}else{
			super.insert(bean);
		}
		String[] fieldCode=(String[]) map.get("fieldCode");
		String[] fieldName=(String[]) map.get("fieldName");
		String[] fieldType=(String[]) map.get("fieldType");
		String[] isMust=(String[]) map.get("isMust");
		String[] defaultValue=(String[]) map.get("defaultValue");
		String[] isSingleRow=(String[]) map.get("isSingleRow");
		String[] options=(String[]) map.get("options");
		String[] description=(String[]) map.get("description");
		Map<String, Object> delMap=new HashMap<>();
		delMap.put("model_type", DictUtil.getIdByNameAndEnName("extendModelType","栏目模型"));
		delMap.put("model_id", bean.getId());
		extendFieldService.deleteByMap(delMap);
		if(fieldCode!=null && fieldCode.length>0){
			for(int i=0;i<fieldCode.length;i++){
				ExtendField extendField=new ExtendField();
				extendField.setModelType(DictUtil.getIdByNameAndEnName("extendModelType","栏目模型"));
				extendField.setModelId(bean.getId());
				extendField.setFieldCode(fieldCode[i]);
				extendField.setFieldName(fieldName[i]);
				if(isMust!=null && isMust[i]!=null){
				    extendField.setFieldType(Integer.valueOf(fieldType[i]));
				}
				if(isMust!=null && isMust[i]!=null){
					extendField.setIsMust(Integer.valueOf(isMust[i]));	
				}
				extendField.setDefaultValue(defaultValue[i]);
				if(isSingleRow!=null && isSingleRow[i]!=null){
					extendField.setIsSingleRow(Integer.valueOf(isSingleRow[i]));
				}
				extendField.setOptions(options[i]);
				//extendField.setDescription(description[i]);
				extendField.setSort(i);
				extendField.setStatus(1);
				extendField.setCreateTime(new Date());
				extendFieldService.insert(extendField);
			}
		}
	}
}
