package com.esen.youngcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esen.youngcms.bean.ChannelTemplate;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author fumiao
 * @since 2017-07-27
 */
public interface ChannelTemplateMapper extends BaseMapper<ChannelTemplate> {
	
	List<ChannelTemplate> selectByChannelId(@Param("channelId")Integer channelId);

}