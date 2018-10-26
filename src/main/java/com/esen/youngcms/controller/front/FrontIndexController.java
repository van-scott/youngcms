package com.esen.youngcms.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.esen.youngcms.controller.base.FrontBaseController;
import com.esen.youngcms.core.utils.Constants;
import com.esen.youngcms.service.ChannelService;

@Controller
@RequestMapping("/channel/")
public class FrontIndexController extends FrontBaseController {
	
	@Autowired
	private ChannelService channelService;
	
	
	@RequestMapping("{channelCode}")
	public String index(@PathVariable("channelCode")String channelCode) {
		System.out.println(channelCode);
		return "forward:/static/"+Constants.TEMPLATE_FOLDER+"/channel/"+channelCode+".html";
	}

}
