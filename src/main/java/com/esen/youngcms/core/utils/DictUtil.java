package com.esen.youngcms.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.esen.youngcms.bean.Dict;
import com.esen.youngcms.vo.DictBean;
import com.esen.youngcms.vo.LabelValue;


/**
 * 字典工具类
 * @author fumiao
 *
 */
public class DictUtil {
	
	
	public static List<LabelValue> getDictsByEnName(String enName){
		List<Dict> dicts=DictBean.getDicts();
		List<LabelValue> labelValues=new ArrayList<LabelValue>();
		for(Dict dict:dicts){
			if(enName.equals(dict.getEnName())){
				labelValues.add(new LabelValue(dict.getName(),dict.getId().toString()));
			}
		}
		return labelValues;
	}
	
	public static String getNameById(Integer id){
		List<Dict> dicts=DictBean.getDicts();
		for(Dict dict:dicts){
			if(id.equals(dict.getId())){
				return dict.getName();
			}
		}
		return "";
	}
	
	public  static Integer getIdByNameAndEnName(String enName,String CnName){
		List<Dict> dicts=DictBean.getDicts();
		for(Dict dict:dicts){
			if(CnName.equals(dict.getName()) && enName.equals(dict.getEnName())){
				return dict.getId();
			}
		}
		return null;
	}
	
	public static Integer getIdByNameAndChName(String chName,String CnName){
		List<Dict> dicts=DictBean.getDicts();
		for(Dict dict:dicts){
			if(CnName.equals(dict.getName()) && chName.equals(dict.getChName())){
				return dict.getId();
			}
		}
		return null;
	}
	
    
}
