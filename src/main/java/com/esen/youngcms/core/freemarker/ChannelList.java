package com.esen.youngcms.core.freemarker;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.Channel;
import com.esen.youngcms.core.freemarker.utils.Freemarker;
import com.esen.youngcms.service.ChannelService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.WrappingTemplateModel;
/**
 * 栏目列表
 * @author fumiao-pc
 *
 */
@Repository
public class ChannelList implements TemplateDirectiveModel {
	
	@Autowired
	private ChannelService channelService;

	@Override
	public void execute(Environment env, Map map, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer pId=Freemarker.getInteger(map, "pId");
		Integer size=Freemarker.getInteger(map, "size");
		Integer isNav=Freemarker.getInteger(map, "isNav");
		String fields=Freemarker.getString(map, "fields");
		String sort=Freemarker.getString(map, "sort");
		Wrapper<Channel> ew=new EntityWrapper<>();
     	if(pId!=null){
     		ew.where("p_id={0}",pId);
     	}
     	if(isNav!=null){
     		ew.where("is_nav={0}",1);
     	}
     	if(StringUtils.isNotBlank(fields)){
			String[] f=StringUtils.split(fields,",");
			String[] s=StringUtils.split(sort,",");
			for(int i=0;i<f.length;i++){
				boolean b =false;
				if("asc".equals(s[i])){
					b=true;
				}
				ew.orderBy(f[i],b);
			}
		}
     	Page<Channel> page=new Page<>();
     	page=channelService.selectPage(page,ew);
		loopVars[0]= WrappingTemplateModel.getDefaultObjectWrapper().wrap(page.getRecords());
		body.render(env.getOut());
	}

}
