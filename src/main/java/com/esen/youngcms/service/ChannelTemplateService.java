package com.esen.youngcms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.ChannelTemplate;
import com.esen.youngcms.dao.ChannelTemplateMapper;
@Service
public class ChannelTemplateService extends ServiceImpl<ChannelTemplateMapper,ChannelTemplate> {
	
    @Autowired
    private ChannelTemplateMapper channelTemplateMapper;
    
	public List<ChannelTemplate> selectByChannelId(Integer channelId) {
		return channelTemplateMapper.selectByChannelId(channelId);
	}

	
}
