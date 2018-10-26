package com.esen.youngcms.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.esen.youngcms.core.freemarker.AdvertList;
import com.esen.youngcms.core.freemarker.ChannelList;
import com.esen.youngcms.core.freemarker.ContentList;
import com.esen.youngcms.core.freemarker.ContentView;
import com.esen.youngcms.core.freemarker.DictCnName;
import com.esen.youngcms.core.freemarker.EncodeURL;
import com.esen.youngcms.core.freemarker.ExtendFieldForm;
import com.esen.youngcms.core.freemarker.FlowOperate;
import com.esen.youngcms.core.freemarker.FlowStatusName;
import com.esen.youngcms.core.freemarker.FriendLinkList;
import com.esen.youngcms.core.freemarker.NextContentTitle;
import com.esen.youngcms.core.freemarker.PreContentTitle;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

@org.springframework.context.annotation.Configuration
public class FreemarkerConfig {

	@Autowired
	private Configuration configuration;
	@Autowired
	private ChannelList channelList;
	@Autowired
	private AdvertList advertList;
	@Autowired
	private FriendLinkList friendLinkList;
	@Autowired
	private ContentList contentList;
	@Autowired
	private ContentView contentView;
	@Autowired
	private NextContentTitle nextContentTitle;
	@Autowired
	private PreContentTitle preContentTitle;
	@Autowired
	private DictCnName dictCnName;
	@Autowired
	private EncodeURL encodeURL;
	@Autowired
	private ExtendFieldForm extendFieldForm;
	@Autowired
	private FlowStatusName flowStatusName;
	@Autowired
	private FlowOperate flowOperate;

	@PostConstruct
	public void setSharedVariable() throws TemplateModelException {
		configuration.setSharedVaribles(getSharedVaribles());
	}
	
	public Map<String, Object> getSharedVaribles() {
		Map<String, Object> sharedVaribles = new HashMap<>();
		sharedVaribles.put("_channel_list", channelList);
		sharedVaribles.put("_advert_list", advertList);
		sharedVaribles.put("_friend_link_list", friendLinkList);
		sharedVaribles.put("_content_list", contentList);
		sharedVaribles.put("_content_view", contentView);
		sharedVaribles.put("_next_content_title", nextContentTitle);
		sharedVaribles.put("_pre_content_title", preContentTitle);
		sharedVaribles.put("_dict_cn_name", dictCnName);
		
		sharedVaribles.put("encodeURL", encodeURL);
		sharedVaribles.put("extendFieldForm", extendFieldForm);
		sharedVaribles.put("flowStatusName",flowStatusName);
		sharedVaribles.put("flowOperate",flowOperate);
		sharedVaribles.put("shiro",new ShiroTags());
		return sharedVaribles;
	}

}