package com.esen.youngcms.core.freemarker;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.esen.youngcms.core.utils.DictUtil;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
/**
 * 广告列表
 * @author fumiao-pc
 *
 */
@Repository
public class DictCnName implements TemplateMethodModel {

	@Override
	public Object exec(List arguments) throws TemplateModelException {
		String text="";
		if(arguments.size()>0){
			String dictId=(String) arguments.get(0);
			text=DictUtil.getNameById(Integer.valueOf(dictId));
		}
		return text;
	}
	

}
