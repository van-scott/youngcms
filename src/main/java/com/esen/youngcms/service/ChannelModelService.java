package com.esen.youngcms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.ChannelModel;
import com.esen.youngcms.dao.ChannelModelMapper;
@Service
public class ChannelModelService extends ServiceImpl<ChannelModelMapper,ChannelModel> {
	
    @Autowired
    private ChannelModelMapper channelModelMapper;
    
	public List<ChannelModel> selectByChannelId(Integer channelId) {
		return channelModelMapper.selectByChannelId(channelId);
	}

	public List<ChannelModel> selectAll() {
		return channelModelMapper.selectAll();
	}

	
}
