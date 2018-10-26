package com.esen.youngcms.core.freemarker;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.esen.youngcms.bean.FriendLink;
import com.esen.youngcms.service.FriendLinkService;

import freemarker.core.Environment;
import freemarker.ext.beans.ArrayModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 友情链接
 * @author fumiao-pc
 *
 */
@Repository
public class FriendLinkList implements TemplateDirectiveModel {
	
	@Autowired
	private FriendLinkService friendLinkService;

	@Override
	public void execute(Environment env, Map map, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		List<FriendLink> list=friendLinkService.selectList(new EntityWrapper<>());
		loopVars[0]=new ArrayModel(list.toArray(),new BeansWrapper());  
		body.render(env.getOut()); 
		
	}

}
