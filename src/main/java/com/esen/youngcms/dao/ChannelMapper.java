package com.esen.youngcms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esen.youngcms.bean.Channel;

public interface ChannelMapper extends BaseMapper<Channel>  {

	Channel selectByCode(String code);

}