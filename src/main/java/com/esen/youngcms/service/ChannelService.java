package com.esen.youngcms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.Channel;
import com.esen.youngcms.bean.ChannelModel;
import com.esen.youngcms.bean.ChannelTemplate;
import com.esen.youngcms.dao.ChannelMapper;
import com.esen.youngcms.dao.ChannelModelMapper;
import com.esen.youngcms.dao.ChannelTemplateMapper;

@Service
public class ChannelService extends ServiceImpl<ChannelMapper,Channel> {
	
	@Autowired
	private ChannelModelMapper channelModelMapper;
	@Autowired
	private ChannelTemplateMapper channelTemplateMapper;

	public void insertOrUpdate(Channel channel,String[] models,String[] templates) {
		if(channel.getId()!=null){
			super.updateById(channel);
		}else{
			super.insert(channel);
		}
		ChannelModel channelModel=new ChannelModel();
		channelModel.setChannelId(channel.getId());
		channelModelMapper.delete(new EntityWrapper<>(channelModel));
		ChannelTemplate channelTemplate=new ChannelTemplate();
		channelTemplate.setChannelId(channel.getId());
		channelTemplateMapper.delete(new EntityWrapper<>(channelTemplate));
		for (int i = 0; i < models.length; i++) {
			if(StringUtils.isNotBlank(models[i])){
				ChannelTemplate _channelTemplate=new ChannelTemplate();
				_channelTemplate.setChannelId(channel.getId());
				_channelTemplate.setModelId(Integer.valueOf(models[i]));
				_channelTemplate.setTemplate(templates[i]);
				channelTemplateMapper.insert(_channelTemplate);
				if(!"0".equals(models[i])){
					ChannelModel _channelModel = new ChannelModel();
					_channelModel.setChannelId(channel.getId());
					_channelModel.setModelId(Integer.valueOf(models[i]));
					channelModelMapper.insert(_channelModel);
				}
			}
		}
	}
	
}
