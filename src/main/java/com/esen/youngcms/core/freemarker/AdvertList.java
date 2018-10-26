package com.esen.youngcms.core.freemarker;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.esen.youngcms.bean.Advert;
import com.esen.youngcms.bean.AdvertPosition;
import com.esen.youngcms.core.freemarker.utils.Freemarker;
import com.esen.youngcms.service.AdvertPositionService;
import com.esen.youngcms.service.AdvertService;

import freemarker.core.Environment;
import freemarker.ext.beans.ArrayModel;
import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 广告列表
 * @author fumiao-pc
 *
 */
@Repository
public class AdvertList implements TemplateDirectiveModel {
	
	@Autowired
	private AdvertService advertService;
	
	@Autowired
	private AdvertPositionService advertPositionService;

	@Override
	public void execute(Environment env, Map map, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer advertPositionId=Freemarker.getInteger(map, "advertPositionId");
		
		Integer size=Freemarker.getInteger(map, "size");
		Advert advert=new Advert();
		advert.setAdvertPositionId(advertPositionId);
		List<Advert> adverts=advertService.selectList(new EntityWrapper<>());
		AdvertPosition advertPosition=advertPositionService.selectById(advertPositionId);
		loopVars[0]=new ArrayModel(adverts.toArray(),new BeansWrapper()); 
		loopVars[1]=new BeanModel(advertPosition,new BeansWrapper()); 
		body.render(env.getOut()); 
	}

}
