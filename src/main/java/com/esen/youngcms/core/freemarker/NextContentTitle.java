package com.esen.youngcms.core.freemarker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.esen.youngcms.service.ContentService;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
/**
 * 下面篇文章标题
 * @author fumiao-pc
 *
 */
@Repository
public class NextContentTitle implements TemplateMethodModel {
	
	@Autowired
	private ContentService contentService;
	
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		  String contentId=(String) arguments.get(0);
		  return contentId;
	}

}
