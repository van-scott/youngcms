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
import com.esen.youngcms.bean.Content;
import com.esen.youngcms.core.freemarker.utils.Freemarker;
import com.esen.youngcms.service.ChannelService;
import com.esen.youngcms.service.ContentService;

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
public class ContentList implements TemplateDirectiveModel {
	
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ContentService contentService;

	@Override
	public void execute(Environment env, Map map, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer model=Freemarker.getInteger(map, "model");
		String channelCode=Freemarker.getString(map, "channelCode");
		String fields=Freemarker.getString(map, "fields");
		String sort=Freemarker.getString(map, "sort");
		Integer size=Freemarker.getInteger(map, "size");
		
		Channel channel=new Channel();
		channel.setCode(channelCode);
		channel=channelService.selectOne(new EntityWrapper<>(channel));
		
		Wrapper<Content> ew=new EntityWrapper<>();
		ew.where("channel_id={0}",channel.getId());
		ew.where("model_id={0}",model);
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
		Page<Content> page=new Page<>();
		page=contentService.selectPage(page, ew);
		loopVars[0]= WrappingTemplateModel.getDefaultObjectWrapper().wrap(page.getRecords());
		loopVars[1]= WrappingTemplateModel.getDefaultObjectWrapper().wrap(channel);
		body.render(env.getOut());
	}

}
