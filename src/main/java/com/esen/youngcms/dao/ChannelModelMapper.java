package com.esen.youngcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esen.youngcms.bean.ChannelModel;

public interface ChannelModelMapper extends BaseMapper<ChannelModel> {

	List<ChannelModel> selectByChannelId(@Param("channelId")Integer channelId);

	List<ChannelModel> selectAll();
}